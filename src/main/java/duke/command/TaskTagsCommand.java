package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a command that lists out the tags the given
 * task has been tagged with.
 */
public class TaskTagsCommand extends Command {
    public static final String COMMAND_WORD = "tasktags";

    private int index;

    public TaskTagsCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return tasks.getTags(this.index);
    }
}
