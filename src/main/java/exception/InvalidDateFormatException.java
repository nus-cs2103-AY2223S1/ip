package exception;

import common.Messages;

public class InvalidDateFormatException extends BlobException {
    public String[] getBlobMessages() {
        return new String[] { Messages.MESSAGE_ERROR_INVALID_DATETIME,
            Messages.MESSAGE_USAGE_INPUT_DATETIME };
    }
}
