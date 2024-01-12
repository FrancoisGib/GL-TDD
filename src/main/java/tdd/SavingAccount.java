package tdd;

public class SavingAccount extends BankAccount {
    @Override
    public void debit(double debitValue) throws InvalidValueException, LimitReachedException {
        if (this.computeBalance() >= debitValue)
            super.debit(debitValue);
    }
}
