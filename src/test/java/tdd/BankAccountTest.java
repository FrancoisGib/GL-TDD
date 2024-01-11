package tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankAccountTest {
    private BankAccount account;

    @BeforeEach
    public void init() {
        this.account = new BankAccount();
    }

    @Test
    public void verifyDebitAndCreditInitializationToZero() {
        assertEquals(0, account.getDebit());
        assertEquals(0, account.getCredit());
    }
}
