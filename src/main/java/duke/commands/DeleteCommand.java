package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeArrayOutOfBoundException;
import duke.exceptions.DukeEmptyCommandException;
import duke.exceptions.DukeException;

/**
 * Represents a <code>Command</code> to delete <code>Task</code>
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a <code>DeleteCommand</code>.
     *
     * @param description description of command.
     */
    public DeleteCommand(String description) {
        super(description);
    }

    /**
     * Deletes a task base on command.
     *
     * @param storage Storage object that communicate with local storage.
     */
    public void execute(Storage storage) {

        try {
            if (description.length() <= 7) {
                throw new DukeEmptyCommandException();
            }

            int index = Integer.parseInt(description.substring(7));

            if (index <= 0) {
                throw new DukeArrayOutOfBoundException();
            }
            Ui.printDeleteTask(index - 1);
            TaskList.delete(index - 1);
        } catch (DukeException e) {
            Ui.printError(e.getMessage());
        } catch (NumberFormatException e) {
            Ui.printError("Index can only be Integer");
        }

    }
}
