package commands;

import duke.Ui;
import tasks.Deadlines;
import tasks.Events;
import tasks.Task;
import tasks.TaskList;
import tasks.Todos;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void run(TaskList taskList) {
        String descript = taskList.retrieveTask(index).toString();
        taskList.deleteTask(index);
        Ui.printDeleteStatement(descript, taskList.getSize());
    }

}
