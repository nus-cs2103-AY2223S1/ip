package duke;

import java.util.ArrayList;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * The Duke class that instantiates instances of duke.
 *
 * Duke is a ChatBot that performs different actions
 * based on commands provided by user.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class Duke {
    // Initialise variables
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for ChatBot, Duke.
     *
     * @param filePath Location of save file.
     */
    public Duke(String filePath) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            ui.printErr(e.getMessage());
            this.tasks = new TaskList(new ArrayList<>());
        }
        assert hasValidState() : "Construction Failure: Invalid State";
    }

    /**
     * Returns a list of String of max size 2 to MainWindow.
     * String[0] stores response to the user.
     * String[1] stores the state of the program, where
     * "0" = Program should not quit after this.
     * "1" = Program should quit after this.
     * @param input user input
     * @return a list of String of size 2
     */
    public String[] getResponse(String input) {

        String[] response;

        try {
            Command command = Parser.parse(input);

            String message = command.execute(tasks, ui, storage);
            String exitStatus = command.isExit() ? "1" : "0";
            response = new String[]{message, exitStatus};
            return response;
        } catch (DukeException e) {
            String message = ui.printErr(e.getMessage());
            String exitStatus = "0";
            response = new String[]{message, exitStatus};
            return response;
        }
    }

    /**
     * Implements the class invariant.
     *
     * Perform all checks on the state of the object.
     * One may assert that this method returns true at the end
     * of every public method.
     * @return true if valid State, false otherwise.
     */
    private boolean hasValidState() {
        return isValidUi(this.ui) && isValidStorage(this.storage) && isValidTaskList(this.tasks);
    }

    /**
     * Returns validity of TaskList.
     *
     * @param tasks The specified TaskList.
     * @return true if valid TaskList, false otherwise.
     */
    private boolean isValidTaskList(TaskList tasks) {
        return tasks != null;
    }

    /**
     * Returns validity of Storage.
     *
     * @param storage The specified Storage.
     * @return true if valid Storage, false otherwise.
     */
    private boolean isValidStorage(Storage storage) {
        return storage != null;
    }

    /**
     * Returns validity of Ui.
     *
     * @param ui The specified Ui.
     * @return true if valid Ui, false otherwise.
     */
    private boolean isValidUi(Ui ui) {
        return ui != null;
    }
}
