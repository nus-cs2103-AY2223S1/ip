package duke.parser;

import duke.DukeException;

/**
 * Exception thrown when the data file is corrupted.
 */
public class DataFileCorruptedException extends DukeException {

    public DataFileCorruptedException() {
        super("Data file is corrupted!");
    }
}
