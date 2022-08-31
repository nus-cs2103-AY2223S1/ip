package blob.exception;

/**
 * The BlobException class represents a type of exception that occurs as a result of interaction
 * with the Blob application.
 */
public abstract class BlobException extends Exception {
    public abstract String[] getBlobMessages();
}
