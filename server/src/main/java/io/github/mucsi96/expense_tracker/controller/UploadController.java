package io.github.mucsi96.expense_tracker.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import io.github.mucsi96.expense_tracker.model.AccountStatement;
import io.github.mucsi96.expense_tracker.model.CardStatement;
import io.github.mucsi96.expense_tracker.service.UploadService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UploadController {

    private final UploadService uploadService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload");
        }

        if (file.getSize() > 10485760) {
            return ResponseEntity.badRequest().body("File size must be less than 10MB");
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !originalFilename.endsWith(".csv")) {
            return ResponseEntity.badRequest().body("File extension must be CSV");
        }

        uploadService.detectCSVType(file).ifPresentOrElse(type -> {
            switch (type) {
                case ACCOUNT_STATEMENT:
                    List<AccountStatement> accountStatements = uploadService.parseAccountStatement(file);
                    accountStatements.forEach(System.out::println);
                    break;
                case CARD_STATEMENT:
                    List<CardStatement> cardStatements = uploadService.parseCardStatement(file);
                    cardStatements.forEach(System.out::println);
                    break;
            }
        }, () -> ResponseEntity.badRequest().body("Unsupported CSV header"));

        return ResponseEntity.ok().body("File uploaded successfully");
    }
}
