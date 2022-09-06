package blob.exception;

import blob.common.Messages;

/**
 * The InvalidPriorityException class represents an exception that occurs when the user inputs an
 * inappropriate priority for a task.
 */
public class InvalidPriorityException extends BlobException {

    /**
     * {@inheritDoc}
     */
    public String[] getBlobMessages() {
        return new String[] { Messages.MESSAGE_ERROR_INVALID_PRIO,
            Messages.MESSAGE_USAGE_INPUT_PRIO };
    }
}
