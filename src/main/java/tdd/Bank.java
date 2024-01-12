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
}
