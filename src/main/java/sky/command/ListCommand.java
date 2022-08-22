package sky.command;

import sky.Storage;
import sky.TaskList;
import sky.Ui;

/**
 * The ListCommand class deals with printing the list.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String s = taskList.printTasks();
        if (!s.equals("")) {
            ui.displayText(s);
        }
        return s;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
