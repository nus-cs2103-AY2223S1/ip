package poolsheen;

/**
 * Represents a general Runtime Exception which is thrown when an error occurs in the Poolsheen program.
 */
public class PoolsheenException extends RuntimeException {
    /**
     * A public constructor to initialise a PoolsheenException object.
     *
     * @param input The input given which caused the exception to be thrown.
     */
    PoolsheenException(String input) {
        super(input);
    }

    @Override
    public String toString() {
        return "The Poolsheen program has encountered an error.";
    }
}