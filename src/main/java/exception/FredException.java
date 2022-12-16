package exception;

/**
 * FredException deals with exceptions related the Fred chat bot.
 */
public class FredException extends Exception {

    public FredException() {
        super();
    }

    public FredException(String error) {
        super(error);
    }
}
