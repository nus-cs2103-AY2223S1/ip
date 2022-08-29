package duke.command;

//import util
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

//import exception
import duke.exception.NoTaskFoundExcpetion;

/**
 * Represents a ListCommand object to be called when user inputs 'list'.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private static final String TASK_LIST = "Here are the tasks in your list:";

    /**
     * Executes ListCommand by listing the task stored in tasks.
     *
     * @param tasks every task in tasks to be displayed.
     * @param ui displays all task in tasks.
     * @param storage
     * @throws NoTaskFoundExcpetion when tasks is empty.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoTaskFoundExcpetion {
        if (tasks.isEmpty()) {
            throw new NoTaskFoundExcpetion();
        }
        displayCommand(ui, TASK_LIST, tasks, tasks.getStatus());
    }
}
