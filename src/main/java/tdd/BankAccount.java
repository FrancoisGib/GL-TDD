package tdd;

import lombok.Getter;

public class BankAccount {
    @Getter
    private double credit = 0;

    @Getter
    private double debit = 0;

    public void credit(double creditValue) throws InvalidValueException {
        this.credit = addIfSuperiorToZero(this.credit, creditValue);
    }

    public void debit(double debitValue) throws InvalidValueException {
        this.debit = addIfSuperiorToZero(this.debit, debitValue);
    }

    private static double addIfSuperiorToZero(double baseValue, double addedValue) throws InvalidValueException {
        if (addedValue < 0)
            throw new InvalidValueException();
        return baseValue + addedValue;
    }
}
