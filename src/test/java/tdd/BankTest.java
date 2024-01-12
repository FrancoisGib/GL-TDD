package tdd;

import static org.junit.jupiter.api.Assertions.assertSame;

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
        assertSame(0, bank.getAccounts.size());
    }
}
