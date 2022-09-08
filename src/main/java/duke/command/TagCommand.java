package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tag.Tag;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to tag a specified task.
 */
public class TagCommand extends Command {
    private int index;
    private Tag tag;

    public static final String COMMAND_WORD = "tag";

    public TagCommand(int index, Tag tag) {
        this.index = index;
        this.tag = tag;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return tasks.tag(index, tag);
    }
}
