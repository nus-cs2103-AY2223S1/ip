package tako.command;

import java.io.IOException;

import tako.Storage;
import tako.TakoException;
import tako.TaskList;
import tako.Ui;

import tako.task.Task;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, TakoException {
        Task task = tasks.remove(taskNumber);
        storage.saveToFile(tasks);
        ui.showDelete(task, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
