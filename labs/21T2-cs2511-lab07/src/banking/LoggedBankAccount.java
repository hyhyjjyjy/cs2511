package banking;

import java.util.ArrayList;

public class LoggedBankAccount extends BankAccount {

    ArrayList<String> log;

    public LoggedBankAccount() {
        super();
        log = new ArrayList<>();
    }

    public ArrayList<String> getLog() {
        return log;
    }

    /**
     * @param amount tobe deposit into the account
     * @pre amount > 0
     * @post balance = old balance + amount
     */
    public void deposit(int amount) {
        super.deposit(amount);
        log.add("The amount of money before your deposit: $" + getBalance() + ", You are " +
                "depositing: $" + amount + ". The amount of money after deposit: $" + getBalance());
    }

    /**
     * @param amount tobe withdrawal from the account
     * @pre amount > balance
     * @post balance = old balance - amount
     */
    public void withdrawal(int amount) {
        super.withdrawal(amount);
        log.add("The amount of money before your withdrawal: " + getBalance() + ", You are " +
                "withdrawing: $" + amount + ". The amount of money after withdrawal: $" + getBalance());
    }
}
