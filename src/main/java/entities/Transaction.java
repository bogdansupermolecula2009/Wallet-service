package entities;

import entities.enums.TransactionStatus;
import entities.enums.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Класс описывающий транзакцию
 *
 * @author Bogdan Andrianov
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Transaction {

    /**
     * id транзакции
     */
    private Long transactionId;

    /**
     * id пользователя
     */
    private Long userId;

    /**
     * сумма транзакции
     */
    private BigDecimal amount;

    /**
     * время создания транзакции
     */
    private Instant createdAt;

    /**
     * тип транзакции(DEBIT, CREDIT)
     */
    private TransactionType type;

    /**
     * статус транзакции(SUCCESSFULLY, FAILED)
     */
    @Setter
    private TransactionStatus status;


}
