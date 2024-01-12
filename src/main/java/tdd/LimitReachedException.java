package tdd;

public class LimitReachedException extends Exception {
    public LimitReachedException() {
        super("Limit reached");
    }
}
