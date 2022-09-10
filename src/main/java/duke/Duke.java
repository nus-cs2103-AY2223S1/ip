package duke;

import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import java.io.File;

/**
 * Main code for Duke.
 */
public class Duke {
    private final Storage storage;
    private final TaskList list;
    private final Parser p;

    public Duke() {
        storage = new Storage("data" + File.separator + "taskList.txt");
        list = new TaskList(storage);
        p = new Parser(list);
    }

    public String getResponse(String userInput) {
        String response;
        try {
            response = p.parseInput(userInput, false);
        } catch (DukeException e) {
            assert false;
            return Ui.showErrorOccurred(e);
        }
        return response;
    }
}
