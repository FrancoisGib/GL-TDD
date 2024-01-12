package tdd;

public class SavingAccount extends BankAccount {
    private double interestsRatio;

    public SavingAccount(double interestsRatio) {
        this.interestsRatio = interestsRatio;
    }

    @Override
    public void debit(double debitValue) throws Exception {
        if (this.computeBalance() < debitValue)
            throw new DebitSuperiorToBalanceException();
        super.debit(debitValue);
    }

    private double computeInterests() {
        return this.computeBalance() * this.interestsRatio;
    }
}

