package entities;

import lombok.*;
import services.helpers.IdGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Клас описывающий пользователя
 *
 * @author Bogdan Andrianov
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class User {

    /**
     * id пользователя
     */
    private Long userId;

    /**
     * имя пользователя
     */
    private String userName;

    /**
     * пароль пользователя
     */
    private String password;

    /**
     * баланс пользователя
     */
    @Setter
    private BigDecimal balance;

    /**
     * статус пользователя(online, offline)
     */
    @Setter
    private boolean online;

    /**
     * список транзакций
     */
    private List<Transaction> transactions;

    public User(String userName, String password) {
        userId = IdGenerator.generateId();
        this.userName = userName;
        this.password = password;
        balance = BigDecimal.valueOf(0.0);
        transactions = new ArrayList<>();
        online = false;
    }

}
