package entities;

import entities.enums.TransactionStatus;
import entities.enums.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Класс описывающий транзакцию
 * @author Bogdan Andrianov
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Transaction {

    private Long transactionId;

    private Long userId;

    private BigDecimal amount;

    private Instant createdAt;

    private TransactionType type;

    private TransactionStatus status;

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}
