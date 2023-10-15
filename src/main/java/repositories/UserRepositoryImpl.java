package repositories;

import entities.User;

import java.util.*;

/**
 * Класс для работы с хранилещем пользователей
 * @author Bogdan Andrianov
 */
public class UserRepositoryImpl implements UserRepository {

    private final Map<Long, User> users = new HashMap<>();

    @Override
    public Optional<User> getUserById(Long userId) {
        return Optional.ofNullable(users.get(userId));
    }

    @Override
    public Optional<User> getUserByName(String userName) {

        return users.values().stream()
                .filter(user -> user.getUserName().equals(userName))
                .findAny();
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User addUser(User user) {
        return users.put(user.getUserId(), user);
    }
}
