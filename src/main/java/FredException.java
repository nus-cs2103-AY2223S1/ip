public class FredException extends Exception {
    public FredException(String error) {
        super("Fred: OOPS!!! " + error);
    }
}
