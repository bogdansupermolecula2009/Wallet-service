package repositories;

import entities.Transaction;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс для работы с хранилищем транзакций
 * @author Bogdan Andrianov
 */
public class TransactionRepositoryImpl implements TransactionRepository {

    private final Map<Long, Transaction> transactions = new HashMap<>();

    @Override
    public Optional<Transaction> getTransactionById(Long transactionId) {
        return Optional.ofNullable(transactions.get(transactionId));
    }


    @Override
    public List<Transaction> getTransactionsByUserId(Long userId) {
        return transactions.values().stream()
                .filter(transaction -> transaction.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions.values());
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        return transactions.put(transaction.getTransactionId(), transaction);
    }
}
