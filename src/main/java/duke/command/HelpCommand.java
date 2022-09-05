package duke.command;

import duke.Constants;
import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Prints out all commands that chatbot has.
 */
public class HelpCommand extends Command {
    public HelpCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.setResponse(Constants.HELP_MESSAGE);
        System.out.println(Constants.HELP_MESSAGE);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
