package poolsheen;

/**
 * Represents a Runtime Exception which will be thrown an unknown command is entered.
 */
public class UnknownCommandException extends RuntimeException {
    /**
     * A public constructor to initialise an UnknownCommandException object.
     *
     * @param input The input given which caused the exception to be thrown.
     */
    UnknownCommandException(String input) {
        super(input);
    }

    @Override
    public String toString() {
        return "Poolsheen thinks this command does not exist.";
    }
}