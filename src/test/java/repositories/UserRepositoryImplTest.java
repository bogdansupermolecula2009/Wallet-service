package repositories;

import entities.Transaction;
import entities.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryImplTest {

    UserRepository userRepository = new UserRepositoryImpl();

    User user = new User(1L, "user", "password", BigDecimal.valueOf(0), false, new ArrayList<Transaction>());

    @Test
    void getUserByUsernameReturnedUser() {
        userRepository.addUser(user);
        Assert.assertNotNull(userRepository.getUserByUserName("user"));
    }

    @Test
    void getUserByUsernameReturnedEmptyOptional() {
        userRepository.addUser(user);
        Assertions.assertEquals(Optional.empty(), userRepository.getUserByUserName("user1"));
    }
}