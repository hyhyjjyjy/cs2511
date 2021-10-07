package banking;

import jdk.jfr.Unsigned;

public class BankAccount {

    private int currentBalance;

    public BankAccount() {
        this.currentBalance = 0;
    }

    public int getBalance() {
        return currentBalance;
    }

    public void setBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    /**
     * @param amount how many money are deposit into the account
     * @pre amount > 0
     * @post currentBalance = old currentBalance + amount
     */
    public void deposit(int amount) {
        currentBalance = currentBalance + amount;
    }

    /**
     * @param amount how many money are withdrawal from the account
     * @pre amount > currentBalance
     * @post currentBalance = old currentBalance - amount
     */
    public void withdrawal(int amount) {
        currentBalance = currentBalance - amount;
    }
}