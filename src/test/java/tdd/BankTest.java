package tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
