package services;

import entities.User;

public interface UserService {

    /**
     * Метод для регистрации пользователя
     *
     * @param userName - имя пользователя
     * @param password - пароль
     */
    void registerUser(String userName, String password);

    /**
     * Метод для авторизации пользователя
     * @param userName - имя пользователя
     * @param password - пароль
     */
    void signIn(String userName, String password);

    /**
     * Метод для выхода из приложения
     * @param user - пользователь для которого осуществляется выход из приложения
     */
    void signOut(User user);

}
