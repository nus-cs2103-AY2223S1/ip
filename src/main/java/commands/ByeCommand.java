package commands;

import byu.TaskList;
import byu.Ui;
import exceptions.InvalidIndexException;

/**
 * Represents a command to exit the chatbot.
 */
public class ByeCommand extends Command {

    public void execute(TaskList tasks, Ui ui) throws InvalidIndexException {
        ui.exit();
        tasks.save();
    }

    public boolean isExit() {
        return true;
    }
}
