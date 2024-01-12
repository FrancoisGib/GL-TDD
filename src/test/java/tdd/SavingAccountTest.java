package tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

public class SavingAccountTest extends BankAccountTest {
    private double interestsRatio = 0.5;

    @Override
    protected BankAccount createAccount() {
        return new SavingAccount(interestsRatio);
    }

    @Test
    public void checkCreditsArrayEmptyAtInitialization() {
        assertEquals(0, account.getCredits().size());
    }

    @Test
    public void checkDebitsArrayEmptyAtInitialization() {
        assertEquals(0, account.getDebits().size());
    }

    @Test
    public void checkDebitSuperiorToBalanceExceptionThrownWhenDebitedValueSuperiorToBalance() throws InvalidValueException, LimitReachedException, DebitSuperiorToBalanceException {
        double debitedValue = 1;
        assertThrows(DebitSuperiorToBalanceException.class, () -> account.debit(debitedValue));
    }

    @Override
    @Test
    public void verifyDebitAddedToArrayWhenDebitingAccount() throws Exception {
        account.credit(1);
        super.verifyDebitAddedToArrayWhenDebitingAccount();
    }

    @Override
    @Test
    public void verifyBalanceRightValueWhenDebitingSeveralTimes() throws Exception {
        account.credit(2);
        double debitValue = 1;
        account.debit(debitValue);
        account.debit(debitValue);
        assertEquals(0, account.computeBalance());
    }

    @Override
    @Test
    public void verifyReachedLimitExceptionWhenDebitExceedsLimit() throws InvalidValueException, LimitReachedException {
        account.credit(BankAccount.LIMIT);
        account.credit(1);
        super.verifyReachedLimitExceptionWhenDebitExceedsLimit();
    }

    @Override
    @Test
    public void verifyDebitAddedIntoDebitsHistory() throws Exception {
        double creditValue = 1;
        account.credit(creditValue);
        super.verifyDebitAddedIntoDebitsHistory();
    }

    @Test
    public void checkRightValueComputedWithInterest() throws Exception {
        double balance = 1;
        account.credit(balance);
        Method computeInterests = SavingAccount.class.getDeclaredMethod("computeInterests"); 
        computeInterests.setAccessible(true); 
        double computedValue = (double)computeInterests.invoke(account);
        assertEquals(interestsRatio * balance, computedValue);
    }
}
