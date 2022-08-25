package duke.command;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;


public class UnmarkCommand extends Command{
    private String index;

    /**
     * Constructor for UnmarkCommand
     *
     * @param index The index of the task to be unmarked, in String.
     */
    public UnmarkCommand(String index) {
        this.index = index;
    }

    /**
     * Mark the task at this index as not completed.
     * Throw an Exception if index is invalid.
     *
     * @param ui The user interface.
     * @param tasks The list of tasks.
     * @param storage The local storage file.
     *
     * @throws DukeException If index < 0 or index > number of tasks in task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        try {
            int i = Integer.parseInt(this.index);
            Task task = tasks.get(i-1);
            task.undo();
            System.out.println("Okay, I have marked this task as not yet done:");
            System.out.println(task);
        } catch (IndexOutOfBoundsException e) {

            throw new InvalidInputException(this.index, "unmark");
        }
    }
}
