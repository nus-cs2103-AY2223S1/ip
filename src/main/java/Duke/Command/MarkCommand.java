package Duke.Command;

import Duke.TaskList;
import Duke.Ui;
import Duke.FileStorage.Storage;
import Duke.Task.Task;

/**
 * This class represents the mark command that marks a task as
 * completed.
 */
public class MarkCommand extends Command{

    /** The id of the task to be marked. */
    private int taskID;

    /** The marked task. */
    private Task markedTask;

    /** Constructs the mark command given the id of task to be marked.
     * 
     * @param taskID The id of the task to be marked.
     */
    public MarkCommand(int taskID) {
        this.taskID = taskID;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.markedTask = tasks.markTask(this.taskID);
        storage.writeListToFile(tasks);
        ui.showResponse(String.format("%s\nNow still you have %d tasks in the list.", 
                this.toString(), tasks.getNumOfTasks()));
    }

    @Override public String toString() {
        return String.format("Nice. I've marked this task:\n   %s", 
                this.markedTask.toString());
    }
}
