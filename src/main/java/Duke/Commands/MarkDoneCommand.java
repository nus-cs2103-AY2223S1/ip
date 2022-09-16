package duke.commands;

import duke.exceptions.TaskIndexException;
import duke.tasks.TaskList;
import duke.tasks.Task;

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
