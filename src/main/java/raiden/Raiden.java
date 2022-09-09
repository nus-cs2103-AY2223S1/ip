package raiden;

import raiden.command.Command;
import raiden.parser.Parser;
import raiden.task.TaskList;

/**
 * Represents Raiden Bot that functions and responds according to user's commands.
 */
public class Raiden {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    /**
     * Creates a Raiden bot and initialise the Storage, Ui and TaskList.
     */
    public Raiden() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            this.storage.initialiseSaveFile();
            this.taskList = storage.createTaskList();
        } catch (RaidenException e) {
            this.ui.showError(e);
            taskList = new TaskList();
        }
    }

    /**
     * Returns the String representation of Raiden.
     *
     * @return The String representation of Raiden.
     */
    @Override
    public String toString() {
        return "Raiden";
    }

    /**
     * Returns Raiden's response to the given user's input.
     *
     * @param input The String representation of the user's input.
     * @return The String representation of Raiden's response.
     */
    public String getResponse(String input) {
        while (this.ui.isActive()) {
            try {
                Command command = Parser.parse(input, this.storage, this.taskList, this.ui);
                return command.execute();
            } catch (RaidenException e) {
                return this.ui.showError(e);
            }
        }
        return "";
    }
}
