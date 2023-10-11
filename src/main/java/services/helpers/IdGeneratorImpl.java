package services.helpers;

/**
 * Класс для создания id
 * @author Bogdan Andrianov
 */
public class IdGeneratorImpl implements IdGenerator {
    private static Long id = 1L;

    @Override
    public Long generateId() {
        return id++;
    }
}
