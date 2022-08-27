package blob.exception;

import blob.common.Messages;

public class ErrorLoadingTaskException extends BlobException {
    public String[] getBlobMessages() {
        return new String[] { Messages.MESSAGE_ERROR_LOADING_TASK };
    }
}
