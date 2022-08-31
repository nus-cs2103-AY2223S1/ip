package commands;

import byu.TaskList;
import byu.Ui;

/**
 * Represents a command to list all the tasks in the list.
 */
public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui) {
        tasks.list();
    }

    public boolean isExit() {
        return false;
    }

}
