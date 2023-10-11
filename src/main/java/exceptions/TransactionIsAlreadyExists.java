package exceptions;

public class TransactionIsAlreadyExists extends RuntimeException{
    public TransactionIsAlreadyExists(String message) {
        super(message);
    }
}
