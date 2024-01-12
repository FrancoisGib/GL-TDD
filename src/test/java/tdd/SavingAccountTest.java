package tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SavingAccountTest extends BankAccountTest {
    protected BankAccount createAccount() {
        return new SavingAccount();
    }

    @Test
    public void checkCreditsArrayEmptyAtInitialization() {
        assertEquals(0, account.getCredits().size());
    }

    @Test
    public void checkDebitsArrayEmptyAtInitialization() {
        assertEquals(0, account.getDebits().size());
    }
}
