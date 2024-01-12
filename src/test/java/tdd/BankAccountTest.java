package tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankAccountTest {
    protected BankAccount account;

    protected BankAccount createAccount() {
        return new BankAccount();
    }

    @BeforeEach
    public void init() {
        this.account = this.createAccount();
    }

    @Test
    public void verifyDebitAndCreditInitializationToZero() {
        double sumCredits = 0;
        for (double d : account.getCredits()) 
            sumCredits += d;
        double sumDebits = 0;
        for (double d : account.getDebits())
            sumDebits += d;
        assertEquals(0, sumCredits);
        assertEquals(0, sumDebits);
    }

    @Test
    public void verifyCreditAddedToArrayWhenCreditingAccount() throws InvalidValueException, LimitReachedException {
        double creditValue = 1;
        account.credit(creditValue);
        assertEquals(creditValue, account.getCredit(0));
    }

    @Test
    public void verifyDebitAddedToArrayWhenDebitingAccount() throws InvalidValueException, LimitReachedException {
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
    public void verifyReachedLimitExceptionWhenCreditExceedsLimit() throws InvalidValueException, LimitReachedException {
        double creditValue = BankAccount.LIMIT + 1;
        assertThrows(LimitReachedException.class, () -> account.credit(creditValue));
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
    public void verifyReachedLimitExceptionWhenDebitExceedsLimit() throws InvalidValueException, LimitReachedException {
        double debitValue = BankAccount.LIMIT + 1;
        assertThrows(LimitReachedException.class, () -> account.debit(debitValue));
    }

    @Test
    public void verifyBalanceRightValueWhenCreditingSeveralTimes() throws InvalidValueException, LimitReachedException {
        double creditValue = 1;
        account.credit(creditValue);
        account.credit(creditValue);
        assertEquals(creditValue * 2, account.computeBalance());
    }

    @Test
    public void verifyBalanceRightValueWhenDebitingSeveralTimes() throws InvalidValueException, LimitReachedException {
        double debitValue = 1;
        account.debit(debitValue);
        account.debit(debitValue);
        assertEquals(0 - debitValue * 2, account.computeBalance());
    }

    @Test
    public void verifyBalanceRightValueWhenCreditingAndDebitingSeveralTimes() throws InvalidValueException, LimitReachedException {
        double creditValue = 1;
        double debitValue = 1;
        account.credit(creditValue);
        account.debit(debitValue);
        assertEquals(creditValue - debitValue, account.computeBalance());
    }

    @Test
    public void verifyCreditAddedIntoCreditsHistory() throws InvalidValueException, LimitReachedException {
        double creditValue = 1;
        account.credit(creditValue);
        assertEquals(creditValue, account.getCredit(0));
    }

    @Test
    public void verifyDebitAddedIntoDebitsHistory() throws InvalidValueException, LimitReachedException {
        double debitValue = 1;
        account.debit(debitValue);
        assertEquals(debitValue, account.getDebit(0));
    }
}

