package commands;

import byu.TaskList;
import byu.Ui;

/**
 * A command to do nothing and move on to the next command.
 */
public class NextCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui) {
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String generateResponse(TaskList tasks) {
        return "";
    }
}
