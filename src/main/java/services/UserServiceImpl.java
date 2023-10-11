package services;

import entities.User;
import exceptions.*;
import repositories.UserRepository;
import services.helpers.IdGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Клас для регистрации и авторизации пользователя
 * @author Bogdan Andrianov
 */
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final IdGenerator idGenerator;

    public UserServiceImpl(UserRepository userRepository, IdGenerator idGenerator) {
        this.userRepository = userRepository;
        this.idGenerator = idGenerator;
    }

    @Override
    public void registerUser(String userName, String password) {
        if (userName.isEmpty() || password.isEmpty()) {
            throw new InvalidFieldException("Данное поле не может быть путстым");
        }
        if (userRepository.getUserByUserName(userName).isPresent()) {
            throw new UserIsAlreadyExistsException(String.format("Пользователь \"%s\" уже существует", userName));
        }
        User userToRegister = User.builder()
                .userId(idGenerator.generateId())
                .userName(userName)
                .password(password)
                .balance(BigDecimal.valueOf(0.0))
                .online(false)
                .transactions(new ArrayList<>())
                .build();
        userRepository.addUser(userToRegister);
    }

    @Override
    public void signIn(String userName, String password) {
        User user = userRepository.getUserByUserName(userName)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        if (!user.getPassword().equals(password)) {
            throw new InvalidPasswordException("Пароль введен неверно");
        }
        user.setOnline(true);
    }

    @Override
    public void signOut(User user) {
        user.setOnline(false);
    }


}
