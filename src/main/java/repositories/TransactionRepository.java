package repositories;

import entities.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {

    /**
     * Метод полученеия транзакции по id
     *
     * @param transactionId - id транзакции
     * @return - транзакция или пустой Optional
     */
    Optional<Transaction> getTransactionById(Long transactionId);

    /**
     * Метод получения транзакции конкретного пользователя
     *
     * @param userId - id пользователя
     * @return - транзакция или пустой Optional
     */
    Optional<Transaction> getTransactionByUserId(Long userId);

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
