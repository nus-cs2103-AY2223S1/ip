package Duke.Command;

import Duke.TaskList;
import Duke.Ui;
import Duke.FileStorage.Storage;
import Duke.Task.Task;

/**
 * This class represents the add command that inserts new tasks
 * into the todo list.
 */
public class AddCommand extends Command{

    /** The task to be inserted into todo list. */
    private Task task;

    /** Constructs the task insertion command.
     * 
     * @param task The task to be inserted.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.insertTask(this.task);
        storage.writeListToFile(tasks);
        ui.showResponse(String.format("%s\nNow you have %d tasks in the list.", 
                this.toString(), tasks.getNumOfTasks()));
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
