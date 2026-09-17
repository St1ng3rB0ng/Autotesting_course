package student.anatoliy;

import java.math.BigDecimal;
import java.util.Objects;

// ToDo: transactions like deposit/withdraw have to be done through TransactionService
public class BankAccount {
    private final Long id;
    private BigDecimal balance;

    public BankAccount(Long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * @param amount an amount of currency to deposit
     */
    public boolean deposit(BigDecimal amount) {
        Objects.requireNonNull(amount);
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalAmountException("Deposit amount must be greater then 0");
        }
        this.balance = balance.add(amount);
        return true;
    }

    /**
     * @param amount an amount of currency to withdraw
     */
    public boolean withdraw(BigDecimal amount) {
        Objects.requireNonNull(amount);
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalAmountException("Withdraw amount must be greater then 0");
        }
        if(balance.subtract(amount).compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalAmountException("Not enough currency to withdraw");
        }
        this.balance = balance.subtract(amount);
        return true;
    }
}
