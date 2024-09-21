package io.github.mucsi96.expense_tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        return "ok";
    }
}
