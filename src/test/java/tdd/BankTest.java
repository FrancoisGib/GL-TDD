package tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void checkBankAccountCreditedWhenCreditingABankAccount() throws Exception {
        bank.openBankAccount();
        this.checkAccountCreditedWhenCreditingAnAccountImplementation();
    }

    @Test
    public void checkSavingAccountCreditedWhenCreditingASavingAccount() throws Exception {
        double interestsRatio = 0.5;
        bank.openSavingAccount(interestsRatio);
        this.checkAccountCreditedWhenCreditingAnAccountImplementation();
    }

    private void checkAccountCreditedWhenCreditingAnAccountImplementation() throws Exception {
        BankAccount account = bank.getAccount(0);
        Double creditedValue = 1.;
        bank.creditAccount(account, creditedValue);
        Double credit = account.getCredits().get(0);
        assertEquals(creditedValue, credit);
    }

    @Test
    public void checkBankAccountDebitedWhenDebitingABankAccount() throws Exception {
        bank.openBankAccount();
        this.checkAccountDebitedWhenDebitingAnAccountImplementation();
    }

    @Test
    public void checkSavingAccountDebitedWhenDebitingASavingAccount() throws Exception {
        double interestsRatio = 0.5;
        bank.openSavingAccount(interestsRatio);
        BankAccount account = bank.getAccount(0);
        account.credit(1);
        this.checkAccountDebitedWhenDebitingAnAccountImplementation();
    }

    private void checkAccountDebitedWhenDebitingAnAccountImplementation() throws Exception {
        BankAccount account = bank.getAccount(0);
        Double debitedValue = 1.;
        bank.debitAccount(account, debitedValue);
        Double debit = account.getDebits().get(0);
        assertEquals(debitedValue, debit);
    }

    @Test
    public void checkAccountUnknownExceptionThrownWhenGettingAnAccountWithAWrongID() {
        assertThrows(AccountUnknownException.class, () -> bank.getAccount(0));
    }
}
