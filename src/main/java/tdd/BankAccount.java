package tdd;

import lombok.Getter;

public class BankAccount {
    @Getter
    private double[] credits;

    @Getter
    private double[] debits;

    public static double LIMIT = 100000;

    public BankAccount(int historiesSize) {
        this.credits = new double[historiesSize];
        this.debits = new double[historiesSize];
    }

    public void credit(double creditValue) throws InvalidValueException, LimitReachedException {
        this.handleAddedValue(this.credits, creditValue);
        this.credits = addValueInArray(this.credits, creditValue);
    }

    public void debit(double debitValue) throws InvalidValueException, LimitReachedException {
        this.handleAddedValue(this.debits, debitValue);
        this.debits = addValueInArray(this.debits, debitValue);
    }

    private void handleAddedValue(double[] array, double addedValue) throws InvalidValueException, LimitReachedException {
        if (addedValue <= 0)
            throw new InvalidValueException();
        else if (sum(array) + addedValue > LIMIT)
            throw new LimitReachedException();
    }

    private static double[] addValueInArray(double[] array, double addedValue) {
        int index = findFirstZeroInArray(array);
        if (index != -1)
            array[index] = addedValue;
        else {
            double[] newArray = new double[array.length];
            newArray[0] = sum(array) + addedValue;
            array = newArray;
        }
        return array;
    }

    private static int findFirstZeroInArray(double[] array) {
        int cpt = 0;
        for (double d : array) {
            if (d == 0)
                return cpt;
            cpt++;
        }
        return -1;
    }

    public double computeBalance() {
        return sum(this.credits) - sum(this.debits);
    }

    private static double sum(double[] array) {
        double sum = 0;
        for (double d : array) {
            sum += d;
        }
        return sum;
    }

    public double getCredit(int i) {
        return this.credits[i];
    }

    public double getDebit(int i) {
        return this.debits[i];
    }
}
