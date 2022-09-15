package Duke.Commands;

import Duke.Exceptions.TaskIndexException;
import Duke.Tasks.TaskList;
import Duke.Tasks.Task;

/**
 * Class that denotes the command of marking a task as done.
 */
public class MarkDoneCommand extends UserCommand {
    private int index;

    /**
     * Public constructor of MarkDoneCommand class.
     * @param index Task index that refers to the task to be marked as done.
     * @param tasks TaskList containing current tasks.
     */
    public MarkDoneCommand(int index, TaskList tasks) {
        super(tasks);
        this.index = index;
    }
    @Override
    public String execute() throws TaskIndexException {
        Task MarkedTask;
        try {
            MarkedTask = this.tasks.markAsDone(this.index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexException();
        }
        String output = String.format("Nice! You have successfully marked the task as done: \n%s\n", MarkedTask);

        return output;
    }


}
