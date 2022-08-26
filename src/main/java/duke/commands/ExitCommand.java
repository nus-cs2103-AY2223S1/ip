package duke.commands;

import duke.exceptions.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents an executable <code>Command</code> to exit <code>Duke</code>.
 */
public class ExitCommand extends Command {

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
     * @param ui <code>Ui</code> to print messages after the command executes.
     * @param storage <code>Storage</code> that interacts with the local storage.
     * @throws DukeException If <code>Storage</code> fails to write into the local storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks);
        ui.printBye();
    }

    /**
     * Returns whether the command is the exit command.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
