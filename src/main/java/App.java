import configuration.liquibase.MigrationLiquibase;
import in.MainController;
import repositories.TransactionRepository;
import repositories.TransactionRepositoryImpl;
import repositories.UserRepository;
import repositories.UserRepositoryImpl;
import services.UserService;
import services.UserServiceImpl;
import services.WalletService;
import services.WalletServiceImpl;
import services.helpers.TransactionFactory;

/**
 * Главный класс который запускает приложение
 */
public class App {

    public static void main(String[] args) {

        MigrationLiquibase.migrate();

        UserRepository userRepository = new UserRepositoryImpl();
        TransactionRepository transactionRepository = new TransactionRepositoryImpl();
        TransactionFactory transactionFactory = new TransactionFactory();
        UserService userService = new UserServiceImpl(userRepository);
        WalletService walletService = new WalletServiceImpl(userRepository, transactionRepository, transactionFactory);
        MainController mainController = new MainController(userService, userRepository, walletService);

        mainController.printMainMenu();
    }

}
