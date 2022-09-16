package blob.exception;

/**
 * The BlobException class represents a type of exception that occurs as a result of interaction
 * with the Blob application.
 */
public abstract class BlobException extends Exception {

    /**
     * Returns the responses to be printed to the user due to the encountered exception.
     *
     * @return The responses to be printed to the user due to the encountered exception
     */
    public abstract String[] getBlobMessages();
}
