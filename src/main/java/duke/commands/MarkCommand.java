package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a command to mark a task in the
 * task list as done.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    private int index;

    /**
     * Constructs a Mark Command with the task to be marked.
     *
     * @param index Index of the task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * This command marks the specified task in the task list
     * as done.
     *
     * @param tasks Contains the task list.
     * @param storage Storage to save and load tasks from a local file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return tasks.mark(index);
    }

}
