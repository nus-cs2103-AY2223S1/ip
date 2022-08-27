package blob.exception;

import blob.common.Messages;

public class UnknownCommandException extends BlobException {
    public String[] getBlobMessages() {
        return new String[] {Messages.MESSAGE_ERROR_UNKNOWN_COMMAND};
    }
}
