package duke;

import java.util.ArrayList;

public class ShowListCommand extends Command {
    @Override
    String execute(String taskName, ArrayList<Task> listOfTasks, Ui ui, Storage storage) {
        TaskList taskList = new TaskList(listOfTasks);
        return taskList.showList();
    }
}
