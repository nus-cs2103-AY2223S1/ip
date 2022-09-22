package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeException;

/**
 * Represents an executable <code>Command</code> to exit <code>Duke</code>.
 */
public class ExitCommand extends Command {

    private static final String MESSAGE_EXIT = "Goodbye!";

    /**
     * Constructs a <code>ExitCommand</code> command.
     *
     * @param description Input from the user.
     */
    public ExitCommand(String description) {
        super(description);
    }

    /**
     * Exits <code>Duke</code> and writes the tasks in the <code>TaskList</code> into the local storage.
     *
     * @param tasks <code>TaskList</code> to be interacted with this command.
     * @param storage <code>Storage</code> that interacts with the local storage.
     * @throws DukeException If <code>Storage</code> fails to write into the local storage.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        storage.save(tasks);
        String response = MESSAGE_EXIT;
        return response;
    }
}
