package sky.command;

import sky.TaskList;
import sky.storage.History;
import sky.storage.Storage;

/**
 * The ListCommand class deals with printing the list.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, Storage storage, History history) {
        String s = taskList.printTasks();
        return s;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
