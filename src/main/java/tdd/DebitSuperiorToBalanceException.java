package tdd;

public class DebitSuperiorToBalanceException extends Exception {
    public DebitSuperiorToBalanceException() {
        super("Debited Amount superior to balance");
    }
}
