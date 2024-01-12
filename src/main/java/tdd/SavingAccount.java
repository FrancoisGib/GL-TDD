package tdd;

public class SavingAccount extends BankAccount {
    @Override
    public void debit(double debitValue) throws Exception {
        if (this.computeBalance() < debitValue)
            throw new DebitSuperiorToBalanceException();
        super.debit(debitValue);
    }
}
