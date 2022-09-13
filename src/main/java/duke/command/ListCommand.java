package duke.command;

import duke.exception.NoTaskFoundException;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Represents a ListCommand object to be called when user inputs 'list'.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private static final String TASK_LIST = "Alright, here are all your tasks:";

    /**
     * Executes ListCommand by listing the task stored in tasks.
     *
     * @param tasks every task in tasks to be displayed.
     * @param storage
     * @return list command message
     * @throws NoTaskFoundException when tasks is empty.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws NoTaskFoundException {
        if (tasks.isEmpty()) {
            throw new NoTaskFoundException();
        }
        return String.format("%s\n%s\n%s", TASK_LIST, tasks, tasks.getStatus());
    }
}
