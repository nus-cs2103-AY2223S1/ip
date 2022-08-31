package blob.exception;

import blob.common.Messages;

/**
 * The UnknownCommandException class represents an exception that occurs when the input user
 * command does not match any of the application's accepted commands.
 */
public class UnknownCommandException extends BlobException {
    public String[] getBlobMessages() {
        return new String[] {Messages.MESSAGE_ERROR_UNKNOWN_COMMAND};
    }
}
