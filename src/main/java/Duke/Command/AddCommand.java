package Duke.Command;

import Duke.TaskList;
import Duke.Ui;
import Duke.FileStorage.Storage;
import Duke.Task.Task;

/**
 * This class represents the add command that inserts new tasks
 * into the task list.
 */
public class AddCommand extends Command{

    /** The task to be inserted into task list. */
    private Task task;

    /** Constructs the task insertion command.
     * 
     * @param task The task to be inserted.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        boolean isInsertSuccessful = tasks.insertTask(this.task);
        assert isInsertSuccessful : "Insertion should be completed successfully";
        boolean isWriteSuccessful = storage.writeListToFile(tasks);
        assert isWriteSuccessful : "Writing to file should be completed successfully";
        return String.format("%s\nNow you have %d tasks in the list.", 
                this.toString(), tasks.getNumOfTasks());
    }

    @Override public String toString() {
        return String.format("Got it. I've added this task:\n    %s", this.task.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddCommand) {
            return ((AddCommand) obj).task.equals(this.task);
        } 
        return false;
    }
}
