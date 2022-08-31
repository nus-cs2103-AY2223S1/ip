package sky.command;

import sky.Storage;
import sky.TaskList;

/**
 * The ListCommand class deals with printing the list.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String s = taskList.printTasks();
        return s;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
