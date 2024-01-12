package tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {
    private Bank bank;

    @BeforeEach
    public void init() {
        this.bank = new Bank();
    }

    @Test
    public void checkBankEmptyAtInitialization() {
        assertEquals(0, bank.getAccounts().size());
    }

    @Test
    public void checkBankAccountAddedToAccountsWhenOpeningABankAccount() {
        bank.openBankAccount();
        assertEquals(1, bank.getAccounts().size());
    }

    @Test
    public void checkSavingAccountAddedToAccountsWhenOpeningASavingAccount() {
        double interestsRatio = 0.5;
        bank.openSavingAccount(interestsRatio);
        assertEquals(1, bank.getAccounts().size());
    }

    @Test
    public void checkBankAccountCreditedWhenCreditingABankAccount() {
        bank.openBankAccount();
        this.checkAccountCreditedWhenCreditingAnAccountImplementation();
    }

    @Test
    public void checkSavingAccountCreditedWhenCreditingASavingAccount() {
        double interestsRatio = 0.5;
        bank.openSavingAccount(interestsRatio);
        this.checkAccountCreditedWhenCreditingAnAccountImplementation();
    }

    private void checkAccountCreditedWhenCreditingAnAccountImplementation() {
        BankAccount account = bank.getAccount(0);
        Double creditedValue = 1.;
        bank.creditAccount(account, creditedValue);
        Double credit = account.getCredits().get(0);
        assertSame(creditedValue, credit);
    }

    @Test
    public void checkBankAccountDebitedWhenDebitingABankAccount() {
        bank.openBankAccount();
        this.checkAccountCreditedWhenCreditingAnAccountImplementation();
    }

    @Test
    public void checkSavingAccountDebitedWhenDebitingASavingAccount() {
        double interestsRatio = 0.5;
        bank.openSavingAccount(interestsRatio);
        BankAccount account = bank.getAccount(0);
        account.credit(1);
        this.checkAccountCreditedWhenCreditingAnAccountImplementation();
    }

    private void checkAccountDebitedWhenDebitingAnAccountImplementation() {
        BankAccount account = bank.getAccount(0);
        Double debitedValue = 1.;
        bank.debitAccount(account, debitedValue);
        Double debit = account.getDebits().get(0);
        assertSame(debitedValue, debit);
    }
}
