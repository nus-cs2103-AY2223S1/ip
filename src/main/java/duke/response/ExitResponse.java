package duke.response;

import duke.DukeException;
import javafx.application.Platform;

/**
 * A DukeResponse that ends Duke.
 */
public class ExitResponse extends DukeResponse {
    @Override
    public String run() throws DukeException {
        Platform.exit();
        return "";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
