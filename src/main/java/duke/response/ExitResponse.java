package duke.response;

import duke.DukeException;

/**
 * A DukeResponse that ends Duke.
 */
public class ExitResponse extends DukeResponse {
    @Override
    public void run() throws DukeException {

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
