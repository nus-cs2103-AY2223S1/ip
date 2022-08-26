package Duke.Command;

import Duke.TaskList;
import Duke.Ui;
import Duke.FileStorage.Storage;
import Duke.Task.Task;

/**
 * This class represents the unmark command that unmarks a task as
 * completed.
 */
public class UnmarkCommand extends Command{

    /** The id of the task to be marked. */
    private int taskID;

    /** The unmarked task. */
    private Task unmarkedTask;

    /** Constructs the mark command given the id of task to be marked.
     * 
     * @param taskID The id of the task to be marked.
     */
    public UnmarkCommand(int taskID) {
        this.taskID = taskID;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.unmarkedTask = tasks.unmarkTask(this.taskID);
        storage.writeListToFile(tasks);
        ui.showResponse(String.format("%s\nNow still you have %d tasks in the list.", 
                this.toString(), tasks.getNumOfTasks()));
    }

    @Override public String toString() {
        return String.format("Ok. I've unmarked this task:\n   %s", 
                this.unmarkedTask.toString());
    }
}

