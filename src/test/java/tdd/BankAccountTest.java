package tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankAccountTest {
    private BankAccount account;

    @BeforeEach
    public void init() {
        this.account = new BankAccount(2);
    }

    @Test
    public void verifyDebitAndCreditInitializationToZero() {
        assertEquals(0, account.getCredit(0));
        assertEquals(0, account.getDebit(0));
    }

    @Test
    public void verifyCreditIncrementationWhenCreditingAccount() throws InvalidValueException {
        double creditValue = 1;
        account.credit(creditValue);
        assertEquals(creditValue, account.getCredit(0));
    }

    @Test
    public void verifyDebitIncrementationWhenDebitingAccount() throws InvalidValueException {
        double debitValue = 1;
        account.debit(debitValue);
        assertEquals(debitValue, account.getDebit(0));
    }

    @Test
    public void verifyInvalidValueExceptionThrowsWhenCreditingNegativeValue() {
        double creditValue = -1;
        assertThrows(InvalidValueException.class, () -> account.credit(creditValue));
    }

    @Test
    public void verifyInvalidValueExceptionThrowsWhenCreditingNullValue() {
        double creditValue = 0;
        assertThrows(InvalidValueException.class, () -> account.credit(creditValue));
    }

    @Test
    public void verifyInvalidValueExceptionThrowsWhenDebitingNegativeValue() {
        double debitValue = -1;
        assertThrows(InvalidValueException.class, () -> account.debit(debitValue));
    }

    @Test
    public void verifyInvalidValueExceptionThrowsWhenDebitingNullValue() {
        double debitValue = 0;
        assertThrows(InvalidValueException.class, () -> account.debit(debitValue));
    }

    @Test
    public void verifyBalanceRightValueWhenCreditingSeveralTimes() throws InvalidValueException {
        double creditValue = 1;
        account.credit(creditValue);
        account.credit(creditValue);
        assertEquals(creditValue * 2, account.computeBalance());
    }

    @Test
    public void verifyBalanceRightValueWhenDebitingSeveralTimes() throws InvalidValueException {
        double debitValue = 1;
        account.debit(debitValue);
        account.debit(debitValue);
        assertEquals(0 - debitValue * 2, account.computeBalance());
    }

    @Test
    public void verifyBalanceRightValueWhenCreditingAndDebitingSeveralTimes() throws InvalidValueException {
        double creditValue = 1;
        double debitValue = 1;
        account.credit(creditValue);
        account.debit(debitValue);
        assertEquals(creditValue - debitValue, account.computeBalance());
    }

    @Test
    public void verifyCreditAddedIntoCreditsHistoryWhenArrayNotFull() throws InvalidValueException  {
        double creditValue = 1;
        account.credit(creditValue);
        assertEquals(creditValue, account.getCredit(0));
    }

    @Test
    public void verifyDebitAddedIntoCreditsHistoryWhenArrayNotFull() throws InvalidValueException  {
        double debitValue = 1;
        account.debit(debitValue);
        assertEquals(debitValue, account.getDebit(0));
    }

    @Test
    public void verifyCreditAddedIntoCreditsHistoryWhenArrayFull() throws InvalidValueException  {
        double creditValue = 1;
        account.credit(creditValue);
        account.credit(creditValue);
        account.credit(creditValue);
        assertEquals(creditValue * 3, account.getCredit(0));
        assertEquals(0, account.getCredit(1));
    }

    @Test
    public void verifyDebitAddedIntoCreditsHistoryWhenArrayFull() throws InvalidValueException  {
        double debitValue = 1;
        account.debit(debitValue);
        account.debit(debitValue);
        account.debit(debitValue);
        assertEquals(debitValue * 3, account.getDebit(0));
        assertEquals(0, account.getDebit(1));
    }
}

