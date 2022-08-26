package Duke.Command;

import Duke.TaskList;
import Duke.Ui;
import Duke.FileStorage.Storage;
import Duke.Task.Task;

public class DelCommand extends Command{

    private int taskID;
    private Task deletedTask;

    public DelCommand(int taskID) {
        this.taskID = taskID;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.deletedTask = tasks.delTask(this.taskID);
        storage.writeListToFile(tasks);
        ui.showResponse(String.format("%s\nNow you have %d tasks in the list.", 
                this.toString(), tasks.getNumOfTasks()));
    }

    @Override public String toString() {
        return String.format("Noted. I've removed this task:\n   %s", 
                this.deletedTask.toString());
    }
}
