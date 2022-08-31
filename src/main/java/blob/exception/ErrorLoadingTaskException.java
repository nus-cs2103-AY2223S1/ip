package blob.exception;

import blob.common.Messages;

/**
 * The ErrorLoadingTaskException class represents an exception that occurs when the Blob
 * application fails to load user's saved tasks.
 */
public class ErrorLoadingTaskException extends BlobException {
    public String[] getBlobMessages() {
        return new String[] { Messages.MESSAGE_ERROR_LOADING_TASK };
    }
}
