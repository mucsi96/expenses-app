package io.github.mucsi96.expense_tracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.mucsi96.expense_tracker.converter.AccountStatementConverter;
import io.github.mucsi96.expense_tracker.converter.CardStatementConverter;
import io.github.mucsi96.expense_tracker.model.AccountStatement;
import io.github.mucsi96.expense_tracker.model.CardStatement;
import io.github.mucsi96.expense_tracker.model.Expense;
import io.github.mucsi96.expense_tracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public void importAccountExpenses(List<AccountStatement> accountStatements) {
        List<Expense> expenses = accountStatements.stream().map(AccountStatementConverter::toExpense).toList();
        expenseRepository.saveAll(expenses);
    }

    public void importCardExpenses(List<CardStatement> cardStatements) {
        List<Expense> expenses = cardStatements.stream().map(CardStatementConverter::toExpense).toList();
        expenseRepository.saveAll(expenses);
    }

    public List<Expense> getExpenses() {
        return expenseRepository.findAll();
    }
}
