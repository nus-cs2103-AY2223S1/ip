package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
     * @param ui <code>Ui</code> to print messages after the command executes.
     * @param storage <code>Storage</code> that interacts with the local storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.list();
    }

}
