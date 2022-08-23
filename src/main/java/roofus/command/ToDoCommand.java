package roofus.command;

import roofus.Storage;
import roofus.TaskList;
import roofus.Ui;
import roofus.task.ToDo;

public class ToDoCommand extends Command {
    private ToDo task;
    
    public ToDoCommand(ToDo task) {
        this.task = task;
    }
    
    @Override
    public void execute(
            TaskList taskList, Storage storage, Ui ui) {
        taskList.addTask(task);
        ui.addTask(task, taskList.length());
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
