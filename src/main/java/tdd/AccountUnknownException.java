package tdd;

public class AccountUnknownException extends Exception {
    public AccountUnknownException() {
        super("Unknow Account");
    }
}
