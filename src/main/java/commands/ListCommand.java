package commands;

import byu.TaskList;
import byu.Ui;

/**
 * A command to list all the tasks in the TaskList.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.list();
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
