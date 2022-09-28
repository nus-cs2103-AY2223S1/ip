package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * Represents an unknown command for an
 * input command that is not recognised
 */
public class UnknownCommand extends Command {

    private static final String MESSAGE = "I am sorry, but I do " +
            "not understand this command";

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.displayMessage(MESSAGE);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}