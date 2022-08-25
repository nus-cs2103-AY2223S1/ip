package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeArrayOutOfBoundException;
import duke.exceptions.DukeEmptyCommandException;
import duke.exceptions.DukeException;

/**
 * Represents a <code>Command</code> to unmark <code>Task</code>.
 */
public class UnMarkCommand extends Command {

    /**
     * Constructs a <code>MarkCommand</code> object.
     *
     * @param description description of command.
     */
    public UnMarkCommand(String description) {
        super(description);
    }

    /**
     * Marks a task.
     *
     * @param storage Storage object that communicate with local storage.
     */
    public void execute(Storage storage) {

        try {
            if (description.length() <= 7) {
                throw new DukeEmptyCommandException();
            }

            int index = Integer.parseInt(description.substring(7));

            if (index < 0) {
                throw new DukeArrayOutOfBoundException();
            }
            TaskList.unMark(index - 1);
            Ui.printTaskIsUndone(index - 1);
        } catch (DukeException e) {
            Ui.printError(e.getMessage());
        } catch (NumberFormatException e) {
            Ui.printError("Index can only be Integer");
        }

    }
}
