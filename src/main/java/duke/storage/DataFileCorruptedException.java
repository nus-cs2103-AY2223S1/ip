package duke.storage;

import duke.DukeException;

/**
 * Exception thrown when the data file is corrupted.
 */
public class DataFileCorruptedException extends DukeException {

    /**
     * Constructs a new DataFileCorruptedException.
     */
    public DataFileCorruptedException() {
        super("Data file is corrupted!");
    }
}
