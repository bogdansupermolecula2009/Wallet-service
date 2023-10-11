package repositories;

import entities.Transaction;

import java.util.*;

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
    public Optional<Transaction> getTransactionByUserId(Long userId) {
        return transactions.values().stream()
                .filter(transaction -> transaction.getUserId().equals(userId))
                .findAny();
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
