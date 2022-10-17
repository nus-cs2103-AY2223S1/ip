package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents an unknown command for an
 * input command that is not recognised
 */
public class UnknownCommand extends Command {

    private static final String UNKNOWN_COMMAND_MESSAGE = "I am really sorry, I do " +
            "not understand this command.\n"
            + "For the list of commands available please type \"help\"";

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.displayMessage(UNKNOWN_COMMAND_MESSAGE);
    }


}