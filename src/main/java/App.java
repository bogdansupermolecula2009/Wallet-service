import in.MainController;
import repositories.TransactionRepository;
import repositories.TransactionRepositoryImpl;
import repositories.UserRepository;
import repositories.UserRepositoryImpl;
import services.UserService;
import services.UserServiceImpl;
import services.WalletService;
import services.WalletServiceImpl;
import services.helpers.IdGenerator;
import services.helpers.IdGeneratorImpl;
import services.helpers.TransactionFactory;

/**
 *
 */
public class App {

    public static void main(String[] args) {
        IdGenerator idGenerator = new IdGeneratorImpl();
        UserRepository userRepository = new UserRepositoryImpl();
        TransactionRepository transactionRepository= new TransactionRepositoryImpl();
        TransactionFactory transactionFactory = new TransactionFactory(idGenerator);
        UserService userService = new UserServiceImpl(userRepository, idGenerator);
        WalletService walletService = new WalletServiceImpl(userRepository, transactionRepository, transactionFactory);
        MainController mainController = new MainController(userService, userRepository, walletService);

        mainController.printMainMenu();
    }
     
}