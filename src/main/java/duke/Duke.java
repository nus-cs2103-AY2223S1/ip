package duke;

import java.io.File;

import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;



/**
 * Main code for Duke.
 */
public class Duke {
    private final Storage storage;
    private final TaskList list;
    private final Parser p;

    /**
     * Constructor for Duke, the main logic of the program.
     */
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
        } catch (ClassCastException f) {
            response = f.getMessage();
        }
        return response;
    }
}
