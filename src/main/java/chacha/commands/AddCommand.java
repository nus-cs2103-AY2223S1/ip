package chacha.commands;

import chacha.Storage;
import chacha.TaskList;
import chacha.Ui;
import chacha.tasks.Task;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }   

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(task);  
        ui.printAdd(task, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}