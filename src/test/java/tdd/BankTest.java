package tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

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
    public void checkAccountUnknownExceptionThrowWhenGettingAnAccountWithAWrongID() {
        assertThrows(AccountUnknownException.class, () -> bank.getAccount(0));
    }

    @Test
    public void checkAccountUnknownExceptionThrowWhenTransferingToAnUnknownAccount() {
        bank.openBankAccount();
        double firstAccountID = 0;
        double unknownAccountID = 1;
        checkAccountUnknownExceptionThrownWhenTransferingImplementation(firstAccountID, unknownAccountID);
    }

    @Test
    public void checkAccountUnknownExceptionThrowWhenTransferingFromAnUnknownAccount() {
        bank.openBankAccount();
        double firstAccountID = 0;
        double unknownAccountID = 1;
        checkAccountUnknownExceptionThrownWhenTransferingImplementation(unknownAccountID, firstAccountID);
    }

    private void checkAccountUnknownExceptionThrownWhenTransferingImplementation(double firstAccountID, double secondAccountID) {
        double transferValue = 1;
        assertThrows(AccountUnknownException.class, () -> bank.transfer(firstAccountID, secondAccountID, transferValue));
    }

    @Test
    public void checkDebitSuperiorToBalanceExceptionThrowWhenTransferringFromASavingAccountWithBalanceAtZero() {
        double interestsRatio = 0.5;
        bank.openSavingAccount(interestsRatio);
        bank.openBankAccount();
        double firstAccountID = 0;
        double secondAccountID = 1;
        double transferValue = 1;
        assertThrows(DebitSuperiorToBalanceException.class, () -> bank.transfer(firstAccountID, secondAccountID, transferValue));
    }

    @Test
    public void checkCreditAddedInDestinationAccountCreditsWhenTransfer() throws Exception {
        bank.openBankAccount();
        bank.openBankAccount();
        double destinationAccountID = 1;
        List<Double> debits = bank.getAccount(1).getCredits();
        checkValueAddedIntoListWhenTransferImplementation(debits, destinationAccountID);
    }

    @Test
    public void checkDebitAddedInOriginAccountDebitsWhenTransfer() throws Exception {
        bank.openBankAccount();
        bank.openBankAccount();
        double originAccountID = 0;
        List<Double> debits = bank.getAccount(0).getDebits();
        checkValueAddedIntoListWhenTransferImplementation(debits, originAccountID);
    }

    private void checkValueAddedIntoListWhenTransferImplementation(List<Double> list, double accountID) {
        double firstAccountID = 0;
        double secondAccountID = 1;
        double transferValue = 1;
        bank.transfer(firstAccountID, secondAccountID, transferValue);
        assertEquals(1, list.get(0));
    }
}
