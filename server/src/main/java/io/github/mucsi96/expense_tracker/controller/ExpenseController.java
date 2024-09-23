package io.github.mucsi96.expense_tracker.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.mucsi96.expense_tracker.model.Expense;
import io.github.mucsi96.expense_tracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(produces = "application/json")
public class ExpenseController {
    private final ExpenseService expenseService;

    @GetMapping("/api/expenses")
    public ResponseEntity<List<Expense>> getExpenses() {
        return ResponseEntity.ok().body(expenseService.getExpenses());
    }
}
