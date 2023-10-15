package services.helpers;

import entities.Transaction;
import entities.enums.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Класс-фабрика для создания транзакций
 */
public class TransactionFactory {

    /**
     * Метод создания транзакции
     * @param userId - идентификатор пользователя, пытающегося провести транзакцию
     * @param sum - сумма транзакции
     * @param type - тип транзакции
     * @return - транзакция
     */
    public Transaction createTransaction(Long userId, BigDecimal sum, TransactionType type) {
        return Transaction.builder()
                .transactionId(IdGenerator.generateId())
                .userId(userId)
                .amount(sum)
                .createdAt(Instant.now())
                .type(type)
                .build();
    }
}
