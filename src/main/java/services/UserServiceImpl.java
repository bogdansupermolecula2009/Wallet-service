package services;

import entities.User;
import exceptions.*;
import repositories.UserRepository;

/**
 * Клас для регистрации и авторизации пользователя
 * @author Bogdan Andrianov
 */
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(String userName, String password) throws InvalidFieldException {
        if (userName.isEmpty() || password.isEmpty()) {
            throw new InvalidFieldException("Данное поле не может быть путстым");
        }
        if (userRepository.getUserByName(userName).isPresent()) {
            throw new UserIsAlreadyExistsException(String.format("Пользователь \"%s\" уже существует", userName));
        }
        User userToRegister = new User(userName, password);
        userRepository.addUser(userToRegister);
    }

    @Override
    public void signIn(String userName, String password) throws InvalidFieldException {
        if (userName.isEmpty() || password.isEmpty()) {
            throw new InvalidFieldException("Данное поле не может быть путстым");
        }
        User user = userRepository.getUserByName(userName)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        if (!user.getPassword().equals(password)) {
            throw new InvalidFieldException("Пароль введен неверно");
        }
        user.setOnline(true);
    }

    @Override
    public void signOut(User user) {
        user.setOnline(false);
    }


}
