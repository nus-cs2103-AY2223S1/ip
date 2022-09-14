package poolsheen;

/**
 * Represents a general Runtime Exception which is thrown when an error occurs in the Poolsheen program.
 */
public class PoolsheenException extends RuntimeException {
    /** A horiontal line of ! to distinguish an error the user has with Poolsheen. */
    private static final String ERROR_LINE = "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";

    /** The type of exception that was passed in. */
    private String errorType;

    /** Poolsheen's response to this error. */
    private String poolsheenResponse;

    /**
     * A public constructor to initialise a PoolsheenException object.
     *
     * @param errorMsg The input given which caused the exception to be thrown.
     * @param errorType The error type of the exception.
     * @param poolsheenResponse The string response that poolsheen will output.
     */
    public PoolsheenException(String errorMsg, String errorType, String poolsheenResponse) {
        super(errorMsg);
        this.errorType = errorType;
        this.poolsheenResponse = poolsheenResponse;
    }

    @Override
    public String toString() {
        return ERROR_LINE + "\n"
                + "Meowww... that looks like a " + this.errorType + " error to me :(\n"
                + "Poolsheen thinks that you should: " + this.poolsheenResponse + "\n"
                + ERROR_LINE;
    }
}
