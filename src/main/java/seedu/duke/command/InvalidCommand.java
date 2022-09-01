package seedu.duke.command;

import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * A class which extends from the Command abstract class.
 * A InvalidCommand object can be used to display to the user whether the given command is valid.
 */
public class InvalidCommand extends Command {
    /**
     * Creates a InvalidCommand object.
     */
    public InvalidCommand() {
        super(false);
    }

    /**
     * Displays an error to the user that it cannot recognise what the user just sent.
     *
     * @param tasks The tasks object containing all the tasks and CRUD methods to modify the tasks.
     * @param ui The Ui object capable of displaying user interface.
     * @param storage The storage object capable of doing write, load, open functionality.
     * @return the reply from the bot
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showError("I'm sorry, but I don't know what that means :-(");
    }
}
