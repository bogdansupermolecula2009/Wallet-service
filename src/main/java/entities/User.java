package entities;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;


/**
 * Клас описывающий пользователя
 * @author Bogdan Andrianov
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class User {

    private Long userId;

    private String userName;

    private String password;

    private BigDecimal balance;

    private boolean online;

    private List<Transaction> transactions;

    public void setOnline(boolean online) {
        this.online = online;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
