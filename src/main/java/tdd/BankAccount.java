package tdd;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class BankAccount {
    @Getter
    protected List<Double> credits = new ArrayList<>();

    @Getter
    protected List<Double> debits = new ArrayList<>();

    public static double LIMIT = 100000;

    public void credit(double creditValue) throws InvalidValueException, LimitReachedException {
        this.handleAddedValue(this.credits, creditValue);
        this.credits.add(creditValue);
    }

    public void debit(double debitValue) throws Exception {
        this.handleAddedValue(this.debits, debitValue);
        this.debits.add(debitValue);
    }

    private void handleAddedValue(List<Double> array, double addedValue) throws InvalidValueException, LimitReachedException {
        if (addedValue <= 0)
            throw new InvalidValueException();
        else if (addedValue > LIMIT)
            throw new LimitReachedException();
    }

    public double computeBalance() {
        return sum(this.credits) - sum(this.debits);
    }

    private static double sum(List<Double> array) {
        double sum = 0;
        for (double d : array) {
            sum += d;
        }
        return sum;
    }

    public double getCredit(int i) {
        return this.credits.get(i);
    }

    public double getDebit(int i) {
        return this.debits.get(i);
    }
}
