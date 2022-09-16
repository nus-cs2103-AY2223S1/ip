package duke.commands;

import duke.exceptions.TaskIndexException;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Class that denotes the command of deleting a task.
 */
public class DeleteTaskCommand extends UserCommand {
    private int index;

    /**
     * Public constructor of DeleteTaskCommand class.
     * @param index Task index that refers to the task to delete.
     * @param tasks TaskList containing current tasks.
     */
    public DeleteTaskCommand(int index, TaskList tasks) {
        super(tasks);
        this.index = index;
    }
    @Override
    public String execute() throws TaskIndexException {
        Task removedTask;
        try {
            removedTask = this.tasks.deleteTask(this.index); //  -1 in the delete task itself
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexException();
        }

        String output = String.format("Nice! You have successfully removed this task:\n%s\n", removedTask);
        return output;
    }
}
