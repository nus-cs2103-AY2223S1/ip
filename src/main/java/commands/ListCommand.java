package commands;

import common.Ui;
import tasklist.TaskList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList) {
        Ui.printTaskList(taskList);
    }
}
