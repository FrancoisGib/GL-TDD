package tdd;

import lombok.Getter;

public class BankAccount {
    @Getter
    private double credit = 0;

    @Getter
    private double debit = 0;

    public void credit(double creditValue) {
        this.credit += creditValue;
    }

    public void debit(double debitValue) {
        this.debit += debitValue;
    }
}
