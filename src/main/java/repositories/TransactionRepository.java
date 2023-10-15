package repositories;

import entities.Transaction;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс предоставляющий методы для работы с хранилещем транзакций
 *
 * @author Bogdan Andrianov
 */
public interface TransactionRepository {

    /**
     * Метод полученеия транзакции по id
     *
     * @param transactionId - id транзакции
     * @return - транзакция или пустой Optional
     */
    Optional<Transaction> getTransactionById(Long transactionId);

    /**
     * Метод получения транзакций конкретного пользователя
     *
     * @param userId - id пользователя
     * @return - список транзакций
     */
    List<Transaction> getTransactionsByUserId(Long userId);

    /**
     * Метод получения всех транзакций
     *
     * @return - список транзакций
     */
    List<Transaction> getAllTransactions();

    /**
     * Метод добавления транзакции в хранилище
     *
     * @param transaction - транзакция которую необходимо добавить
     * @return - транзакция
     */
    Transaction addTransaction(Transaction transaction);
}
