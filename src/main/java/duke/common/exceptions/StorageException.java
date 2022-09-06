package duke.common.exceptions;

import duke.common.Messages;

/**
 * Represents an exception for errors when saving or loading data files.
 */
public class StorageException extends DukeException {
    /**
     * Constructor for a StorageException.
     */
    public StorageException() {
        super(Messages.MESSAGE_STORAGE_ERROR);
    }
}
