package duke.command;

import duke.exception.TaskMarkException;
import duke.exception.TaskNotFoundException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Represents a MarkCommand object to be called when user inputs 'mark'.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private static final String TASK_MARK = "Splendid, I knew you are efficient! Marking this task as done: ";
    private int index;

    /**
     * Constructs MarkCommand with index to be marked.
     *
     * @param index index to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes MarkCommand by marking the task specified by the index as done.
     *
     * @param tasks list of task where the index specified will be marked as done.
     * @param storage updates the storage when task is marked done.
     * @return mark command message
     * @throws TaskMarkException when task specified by the index is already marked done.
     * @throws TaskNotFoundException when index given is out of range.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskMarkException, TaskNotFoundException {
        Task task = tasks.markTask(index);
        storage.update(tasks);
        return String.format("%s\n%s", TASK_MARK, task);
    }
}
