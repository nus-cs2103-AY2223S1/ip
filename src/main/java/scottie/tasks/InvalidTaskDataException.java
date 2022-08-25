package scottie.tasks;

/**
 * An exception that is thrown when an encoded Task string
 * cannot be parsed.
 */
class InvalidTaskDataException extends Exception {
    /**
     * Constructs an InvalidTaskDataException with the given errorMessage.
     *
     * @param errorMessage The errorMessage to be attached to this exception.
     */
    InvalidTaskDataException(String errorMessage) {
        super(errorMessage);
    }
}
