package services.helpers;

import entities.Transaction;
import entities.enums.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Класс-фабрика для создания транзакций
 */
public class TransactionFactory {

    private final IdGenerator idGenerator;

    public TransactionFactory(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    /**
     * Метод создания транзакции
     * @param userId - идентификатор пользователя, пытающегося провести транзакцию
     * @param sum - сумма транзакции
     * @param type - тип транзакции
     * @return - транзакция
     */
    public Transaction createTransaction(Long userId, BigDecimal sum, TransactionType type) {
        return Transaction.builder()
                .transactionId(idGenerator.generateId())
                .userId(userId)
                .amount(sum)
                .createdAt(Instant.now())
                .type(type)
                .build();
    }
}
