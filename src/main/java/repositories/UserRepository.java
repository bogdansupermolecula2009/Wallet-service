package repositories;

import entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    /**
     * Метод для получения пользователя по id
     * @param userId - id пользователя
     * @return - возвращает пользователя или пустой Optional
     */
    Optional<User> getUserById(Long userId);

    /**
     * Метод для получения пользователя по его имени
     * @param userName - имя пользователя
     * @return - возвращает пользователя или пустой Optional
     */
    Optional<User> getUserByUserName(String userName);

    /**
     * Метод получения всех пользователей
     * @return - список пользователей
     */
    List<User> getAllUsers();

    /**
     * Метод добавления пользователя в хранилище
     * @param user - пользователь которого необходимо добавить
     * @return - пользователь
     */
    User addUser(User user);


}
