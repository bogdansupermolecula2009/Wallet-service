package in;

import entities.User;
import entities.enums.TransactionType;
import exceptions.InvalidFieldException;
import repositories.UserRepository;
import services.UserService;
import services.WalletService;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Scanner;

/**
 * Класс перенаправляющий данные из консоли в аргументы методов
 * @author Bogdan Andrianov
 */
public class MainController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final WalletService walletService;

    public MainController(UserService userService, UserRepository userRepository, WalletService walletService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.walletService = walletService;
    }

    /**
     * Метод печатает главное меню
     */
    public void printMainMenu() {
        System.out.println("\n1) Зарегистрироваться \n2) Войти ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch (input) {
            case "1" -> {
                try {
                    registrationUser();
                } catch (InvalidFieldException e) {
                    System.err.println(e);
                }
                printMainMenu();
            }
            case "2" -> {
                try {
                    signIn();
                    choiceAction();
                } catch (InvalidFieldException e) {
                    System.err.println(e);
                }
                printMainMenu();
            }
        }
    }

    /**
     * Метод выбора определенного действия
     */
    public void choiceAction() {
        System.out.println("\n1) Пополнить счет \n2) Снять средства \n3) История операций \n4)Текущий баланс \n5) Выйти");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch (input) {
            case "1" -> {
                deposit();
                choiceAction();
            }
            case "2" -> {
                withdraw();
                choiceAction();
            }
            case "3" -> getTransactionHistory();

            case "4" -> {
                System.out.println(walletService.getUserBalance(getOnlineUser()));
                choiceAction();
            }

            case "5" -> {
                userService.signOut(userRepository.getUserById(getOnlineUser()).get());
                printMainMenu();
            }
        }
    }

    /**
     * Метод передает параметры из консоли в одноименный метод класса WalletService
     */
    public void deposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите сумму: ");
        String sum = scanner.nextLine();
        walletService.deposit(getOnlineUser(), BigDecimal.valueOf(Long.parseLong(sum)));
    }

    /**
     *Метод передает параметры из консоли в одноименный метод класса WalletService
     */
    public void withdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите сумму: ");
        String sum = scanner.nextLine();
        walletService.withdraw(getOnlineUser(), BigDecimal.valueOf(Long.parseLong(sum)));
    }

    /**
     * Метод передает параметры из консоли в одноименный метод класса WalletService
     */
    public void getTransactionHistory() {
        System.out.println("Выберите тип операции ");
        System.out.println("\n1) Пополнения счета \n2) Вывод средств \n3) Назад");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch (input) {
            case "1" -> {
                System.out.println(walletService.getTransactionHistory(getOnlineUser(), TransactionType.CREDIT));
                getTransactionHistory();
            }
            case "2" -> {
                System.out.println(walletService.getTransactionHistory(getOnlineUser(), TransactionType.DEBIT));
                getTransactionHistory();
            }

            case "3" -> choiceAction();
        }
    }

    /**
     * Метод получения текщего пользователя
     * @return - пользователь со статусом online
     */
    private Long getOnlineUser() {
        Optional<User> onlineUser = userRepository.getAllUsers()
                .stream().filter(User::isOnline).findAny();
        return onlineUser.get().getUserId();
    }

    /**
     * Метод передает параметры из консоли в одноименный метод класса UserService
     */
    public void registrationUser() throws InvalidFieldException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Придумайте имя: ");
        String username = scanner.nextLine();
        System.out.println("Придумайте пароль: ");
        String password = scanner.nextLine();
        userService.registerUser(username, password);

    }

    /**
     *Метод передает параметры из консоли в одноименный метод класса UserService
     */
    public void signIn() throws InvalidFieldException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ваше имя: ");
        String username = scanner.nextLine();
        System.out.println("Введите ваш пароль: ");
        String password = scanner.nextLine();
        userService.signIn(username, password);
    }

}
