package tdd;

public class InvalidValueException extends Exception {
    public InvalidValueException() {
        super("Invalid amount");
    }
}