package commands;

import byu.TaskList;
import byu.Ui;

/**
 * Represents a command to do nothing and move ot the next command.
 */
public class NextCommand extends Command {

    public void execute(TaskList tasks, Ui ui) {
    }

    public boolean isExit() {
        return false;
    }

}
