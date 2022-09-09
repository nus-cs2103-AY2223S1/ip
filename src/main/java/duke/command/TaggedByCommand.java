package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tag.Tag;
import duke.task.TaskList;

/**
 * Represents a command that lists out the tasks tagged by
 * the specified tag.
 */
public class TaggedByCommand extends Command {
    public static final String COMMAND_WORD = "taggedby";

    private Tag tag;

    public TaggedByCommand(Tag tag) {
        this.tag = tag;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return Tag.tasksUnder(this.tag);
    }
}
