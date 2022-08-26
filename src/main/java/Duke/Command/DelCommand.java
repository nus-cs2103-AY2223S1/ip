package Duke.Command;

import Duke.TaskList;
import Duke.Ui;
import Duke.FileStorage.Storage;
import Duke.Task.Task;

/**
 * This class represents the delete command that removes tasks
 * from the todo list.
 */
public class DelCommand extends Command{

    /** The id of the task to be removed. */
    private int taskID;

    /** The tasked removed from the list. */
    private Task deletedTask;

    /** Constructs the task deletion command.
     * 
     * @param task The task to be deleted.
     */
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
