package duke.commands;

import duke.Storage;
import duke.TaskList;

/**
 * Represents an executable <code>Command</code> to list the <code>Task</code>s in the <code>TaskList</code>.
 */
public class ListCommand extends Command {

    /**
     * Constructs a <code>ListCommand</code> command.
     *
     * @param description Input from the user.
     */
    public ListCommand(String description) {
        super(description);
    }

    /**
     * Lists out the <code>Task</code>s in the <code>TaskList</code>.
     *
     * @param tasks <code>TaskList</code> to be interacted with this command.
     * @param storage <code>Storage</code> that interacts with the local storage.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.list();
    }

}
