package duke.response;

import duke.DukeException;

/**
 * A DukeResponse for reading data file.
 */
public class ReadFileResponse extends DukeResponse {
    @Override
    public String run() throws DukeException {
        return "Loading...";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
