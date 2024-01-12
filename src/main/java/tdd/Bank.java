package tdd;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class Bank {
    @Getter
    List<BankAccount> accounts = new ArrayList<>();

    public void openBankAccount() {
        this.accounts.add(new BankAccount());
    }

    public void openSavingAccount(double interestsRatio) {
        this.accounts.add(new SavingAccount(interestsRatio));
    }

    public void creditAccount(BankAccount account, double creditedValue) throws LimitReachedException, InvalidValueException {
        account.credit(creditedValue);
    }

    public void debitAccount(BankAccount account, double creditedValue) throws Exception {
        account.debit(creditedValue);
    }

    public BankAccount getAccount(int i) throws AccountUnknownException {
        if (accounts.size() <= i)
            throw new AccountUnknownException();
        return accounts.get(i);
    }

    public void transfer(double originAccountID, double destinationAccountID, double transferValue) throws Exception {
        BankAccount originAccount = this.getAccount(0);
        BankAccount destinationAccount = this.getAccount(1);
        originAccount.debit(transferValue);
        destinationAccount.credit(transferValue);
    }
}
