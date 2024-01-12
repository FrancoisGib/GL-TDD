package tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class SavingAccountTest extends BankAccountTest {
    @Override
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
}
