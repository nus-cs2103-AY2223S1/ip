package blob.exception;

import blob.common.Messages;

public class InvalidTaskIndexException extends BlobException {
    public String[] getBlobMessages() {
        return new String[] { Messages.MESSAGE_ERROR_MISSING_TASK_INDEX };
    }
}
