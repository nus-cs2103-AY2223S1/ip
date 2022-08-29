package duke.command;

import duke.exception.NoTaskFoundExcpetion;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoTaskFoundExcpetion {
        TaskList filterTasks = tasks.findTask(description);
        if (filterTasks.isEmpty()) {
            throw new NoTaskFoundExcpetion();
        }
        displayCommand(ui, TASK_FIND, filterTasks, filterTasks.getStatus());
    }
}
