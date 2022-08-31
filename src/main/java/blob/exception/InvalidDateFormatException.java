package blob.exception;

import blob.common.Messages;

/**
 * The InvalidDateFormatException class represents an exception that occurs when an input date or
 * datetime does not meet the required formats.
 */
public class InvalidDateFormatException extends BlobException {
    public String[] getBlobMessages() {
        return new String[] { Messages.MESSAGE_ERROR_INVALID_DATETIME,
            Messages.MESSAGE_USAGE_INPUT_DATETIME };
    }
}
