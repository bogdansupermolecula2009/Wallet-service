package services.helpers;

/**
 * Класс для создания id
 * @author Bogdan Andrianov
 */
public class IdGenerator {
    private static Long id = 1L;

    public static Long generateId() {
        return id++;
    }
}
