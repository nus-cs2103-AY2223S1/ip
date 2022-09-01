package amanda.exception;

public class AmandaException extends Exception {
    public AmandaException(String error) {
        super(error);
    }

    @Override
    public String toString() {
        return "Amanda Exception: " + getMessage();
    }
}
