package duke.command;

import duke.exception.NoTaskFoundException;
import duke.exception.TaskDuplicatedException;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Represents a FindCommand object to be called when user inputs 'find'.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private static final String TASK_FIND = "Here are the matching tasks in your list:";
    private String description;

    /**
     * Constructs FindCommand object with the index of the element to delete.
     *
     * @param description String of char to find.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the find command and look in task list for any matches.
     * @param tasks
     * @param storage
     * @return find command message
     * @throws NoTaskFoundException when no task is matches the search
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws NoTaskFoundException, TaskDuplicatedException {
        TaskList filterTasks = tasks.findTask(description);
        if (filterTasks.isEmpty()) {
            throw new NoTaskFoundException();
        }
        return String.format("%s\n%s\n%s", TASK_FIND, filterTasks, filterTasks.getStatus());
    }
}
