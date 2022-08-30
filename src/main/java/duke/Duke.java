package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.task.TaskList;

/**
 * Represents Duke Bot that functions and responds according to user's commands.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    /**
     * Creates a Duke bot and initialise the Storage, Ui and TaskList.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            this.storage.initialiseSaveFile();
            this.taskList = storage.createTaskList();
            this.ui.showMessage("Loading from save...\n");
        } catch (DukeException e) {
            this.ui.showError(e);
            taskList = new TaskList();
        }
    }

    /**
     * Returns the String representation of Duke.
     *
     * @return The String representation of Duke.
     */
    @Override
    public String toString() {
        return "Duke";
    }

    /**
     * Returns Duke's response to the given user's input.
     *
     * @param input The String representation of the user's input.
     * @return The String representation of Duke's response.
     */
    public String getResponse(String input) {
        while (this.ui.isActive()) {
            try {
                Command command = Parser.parse(input, this.storage, this.taskList, this.ui);
                return command.execute();
            } catch (DukeException e) {
                return this.ui.showError(e);
            }
        }
        return "";
    }
}
