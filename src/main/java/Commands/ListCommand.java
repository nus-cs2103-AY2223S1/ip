package Commands;

import TaskList.TaskList;
import common.Ui;

public class ListCommand extends Command {
    public ListCommand() {
    }

    @Override
    public void execute(TaskList taskList) {
        Ui.printTaskList(taskList);
    }
}
