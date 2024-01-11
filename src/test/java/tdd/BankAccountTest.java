package tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void verifyCreditIncrementationWhenCreditingAccount() throws InvalidValueException {
        double creditValue = 1;
        account.credit(creditValue);
        assertEquals(1, account.getCredit());
    }

    @Test
    public void verifyDebitIncrementationWhenDebitingAccount() throws InvalidValueException {
        double debitValue = 1;
        account.debit(debitValue);
        assertEquals(1, account.getDebit());
    }

    @Test
    public void verifyInvalidValueExceptionThrowsWhenCreditingNegativeValue() {
        double creditValue = -1;
        assertThrows(InvalidValueException.class, () -> account.credit(creditValue));
    }

    @Test
    public void verifyInvalidValueExceptionThrowsWhenDebitingNegativeValue() {
        double debitValue = -1;
        assertThrows(InvalidValueException.class, () -> account.debit(debitValue));
    }
}

