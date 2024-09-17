package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tag.Tag;
import duke.task.TaskList;

/**
 * Represents a command to delete a specified tag from a specified task.
 */
public class UntagCommand extends Command {
    private int index;
    private Tag tag;

    public static final String COMMAND_WORD = "untag";

    public UntagCommand(int index, Tag tag) {
        this.index = index;
        this.tag = tag;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return tasks.untag(index, tag);
    }
}
