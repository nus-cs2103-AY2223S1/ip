package duke;

import duke.exception.DukeException;
import duke.ui.Launcher;

/**
 * Duke is the main class of the program to save and keep track of ur task in a text file.
 *
 * @author Lian Guo Yang
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor of Duke to initialise ui for showing messages and storage to store tasks.
     */
    public Duke() {
        ui = new Ui();
        String filePath = "./data/duke.txt";
        storage = new Storage(ui, filePath);
        taskList = new TaskList(storage.load());
    }

    /**
     * Gets response from the user input through the parser and execute commands.
     */
    public String getResponse(String input) throws DukeException {
        assert(ui != null && taskList != null && input != null);
        return Parser.parse(input).execute(ui, taskList, storage);
    }

    public static void main(String[] args) {
        Launcher.main(args);
    }
}
