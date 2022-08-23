package blink.command;

import blink.Storage;
import blink.TaskList;
import blink.Ui;
import blink.task.Task;

public class DeleteCommand extends Command {

    private int num;

    public DeleteCommand(int num) {
        this.num = num;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)  {
        Task temp = tasks.deleteTask(num);
        ui.deleteTask(tasks, temp);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
