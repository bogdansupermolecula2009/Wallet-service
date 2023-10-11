package services;

import entities.Transaction;
import entities.User;
import entities.enums.TransactionStatus;
import entities.enums.TransactionType;
import exceptions.NotFoundException;
import exceptions.TransactionIsAlreadyExists;
import repositories.TransactionRepository;
import repositories.UserRepository;
import services.helpers.TransactionFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс обслуживающий счет пользователя
 *
 * @author Bogdan Andrianov
 */

public class WalletServiceImpl implements WalletService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionFactory transactionFactory;

    public WalletServiceImpl(UserRepository userRepository, TransactionRepository transactionRepository, TransactionFactory transactionFactory) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.transactionFactory = transactionFactory;
    }

    @Override
    public void deposit(Long userId, BigDecimal sum) {
        TransactionType type = TransactionType.CREDIT;
        Transaction transaction = transactionFactory.createTransaction(userId, sum, type);
        if (transactionRepository.getTransactionById(transaction.getTransactionId()).isPresent()) {
            throw new TransactionIsAlreadyExists("id транзакции не уникален");
        } else {
            transactionRepository.addTransaction(transaction);
            User user = getUserOrThrowException(userId);
            user.getTransactions().add(transaction);
            BigDecimal result = user.getBalance().add(sum);
            user.setBalance(result);
            transaction.setStatus(TransactionStatus.SUCCESSFULLY);
        }
    }


    @Override
    public void withdraw(Long userId, BigDecimal sum) {
        TransactionType type = TransactionType.DEBIT;
        Transaction transaction = transactionFactory.createTransaction(userId, sum, type);
        if (transactionRepository.getTransactionById(transaction.getTransactionId()).isPresent()) {
            throw new TransactionIsAlreadyExists("id транзакции не уникален");
        } else {
            transactionRepository.addTransaction(transaction);
            User user = getUserOrThrowException(userId);
            user.getTransactions().add(transaction);
            BigDecimal userBalance = user.getBalance();
            BigDecimal result = user.getBalance().subtract(sum);
            if (userBalance.compareTo(sum) >= 0) {
                user.setBalance(result);
                transaction.setStatus(TransactionStatus.SUCCESSFULLY);
            } else {
                transaction.setStatus(TransactionStatus.FAILED);
            }
        }
    }

    @Override
    public List<Transaction> getTransactionHistory(Long userId, TransactionType transactionType) {
        User user = getUserOrThrowException(userId);
        return user.getTransactions().stream()
                .filter(transaction -> transaction.getType().equals(transactionType))
                .collect(Collectors.toList());
    }


    @Override
    public BigDecimal getUserBalance(Long userId) {
        User user = getUserOrThrowException(userId);
        return user.getBalance();
    }

    /**
     * Вспомогательный метод получения пользователя, если пользователь не найден выбрасывается исключение
     *
     * @param userId - идентификатор пользователя
     * @return - пользователь
     */
    private User getUserOrThrowException(Long userId) {
        return userRepository.getUserById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
    }
}
