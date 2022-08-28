package duke.command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;


/**
 * Class to encapsulate a command that adds Task to list.
 */
public class AddCommand extends Command {

    /**
     * Constructor for AddCommand.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        super(task);
    }

    /**
     * Carry out command to add task to task list.
     *
     * @param ui The user interface.
     * @param tasks The task list.
     * @param storage The local storage file.
     *
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.getTask(), true);
    }
}
