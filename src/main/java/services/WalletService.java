package services;

import entities.Transaction;
import entities.enums.TransactionType;

import java.math.BigDecimal;
import java.util.List;


/**
 * Интерфейс предоставляющий методы для обслуживания счета пользователя
 *
 * @author Bogdan Andrianov
 */
public interface WalletService {

    /**
     * Метод зачисления средств на счет пользователя
     *
     * @param userId - идентификатор пользователя
     * @param sum    - сумма пополнения счета
     */
    void deposit(Long userId, BigDecimal sum);

    /**
     * Метод списания средств со счета пользователя
     *
     * @param userId - идентификатор пользователя
     * @param sum    - сумма списания со счета
     */
    void withdraw(Long userId, BigDecimal sum);

    /**
     * Метод получения текущего баланса пользователя
     *
     * @param userId - идентификатор пользователя
     * @return - баланс пользователя
     */
    BigDecimal getUserBalance(Long userId);

    /**
     * Метод получения истории транзакций пользователя, в зависимости от типа транзакции
     *
     * @param userId          - идентификатор пользователя
     * @param transactionType - тип транзакции
     * @return - список транзакций
     */
    List<Transaction> getTransactionHistory(Long userId, TransactionType transactionType);
}
