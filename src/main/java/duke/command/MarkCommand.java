package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;

/**
 * This class represents the mark command that marks a task as
 * completed.
 */
public class MarkCommand extends Command{

    /** The id of the task to be marked. */
    private int[] taskID;

    /** The marked task. */
    private Task[] markedTask;

    /** Constructs the mark command given the id of task to be marked.
     * 
     * @param taskID The id of the task to be marked.
     */
    public MarkCommand(int[] taskID) {
        this.taskID = taskID;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        this.markedTask = tasks.markTask(this.taskID);
        boolean isWriteSuccessful = storage.writeListToFile(tasks);
        assert isWriteSuccessful : "Writing to file should be completed successfully";
        return String.format("%s\nNow still you have %d tasks in the list.", 
                this.toString(), tasks.getNumOfTasks());
    }

    @Override public String toString() {
        return String.format("Noted. I've marked this task:   %s", 
                TaskList.taskArrToString(this.markedTask));
    }
}
