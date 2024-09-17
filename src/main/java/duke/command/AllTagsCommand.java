package duke.command;

import duke.storage.Storage;
import duke.tag.Tag;
import duke.task.TaskList;

/**
 * Represents a command to print all the tags added by the user.
 */
public class AllTagsCommand extends Command {
    public static final String COMMAND_WORD = "alltags";

    /**
     * {@inheritDoc}
     * This command prints all the tags added to the user.
     *
     * @param tasks Contains the task list.
     * @param storage Storage to save and load tasks from a local file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return Tag.printTags();
    }
}
