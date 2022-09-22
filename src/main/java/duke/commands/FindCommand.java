package duke.commands;

import duke.Storage;
import duke.TaskList;

/**
 * Represents an executable <code>Command</code> to find <code>Task</code>s.
 */
public class FindCommand extends Command {

    /**
     * Constructs a <code>FindCommand</code> command.
     *
     * @param description Input from the user.
     */
    public FindCommand(String description) {
        super(description);
    }

    /**
     * Finds the <code>Task</code>/s that matches the given input.
     *
     * @param tasks <code>TaskList</code> to be interacted with this command.
     * @param storage <code>Storage</code> that interacts with the local storage.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.find(description);
    }
}
