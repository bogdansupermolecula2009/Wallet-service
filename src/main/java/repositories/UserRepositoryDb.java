package repositories;

import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserRepositoryDb implements UserRepository {

    private final Connection connection;

    public UserRepositoryDb(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByName(String userName) {
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User addUser(User user) {
        return null;
    }
}
