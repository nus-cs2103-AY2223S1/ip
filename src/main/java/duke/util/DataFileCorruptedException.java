package duke.util;

import duke.DukeException;

public class DataFileCorruptedException extends DukeException {

    public DataFileCorruptedException() {
        super("Data file is corrupted!");
    }
}
