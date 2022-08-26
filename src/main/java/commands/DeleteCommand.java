package commands;

import duke.Ui;
import tasks.*;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void run(TaskList taskList) {
        String descript = taskList.retrieveTask(index).toString();
        taskList.deleteTask(index);
        Ui.deleteStatement(descript, taskList.getSize());
    }

}
