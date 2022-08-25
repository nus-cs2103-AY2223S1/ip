package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeArrayOutOfBoundException;
import duke.exceptions.DukeEmptyCommandException;
import duke.exceptions.DukeException;

/**
 * Represents a <code>Command</code> to mark <code>Task</code>.
 */
public class MarkCommand extends Command {

    /**
     * Constructs a <code>MarkCommand</code> object.
     *
     * @param description description of command.
     */
    public MarkCommand(String description) {
        super(description);
    }

    /**
     * Marks a task.
     *
     * @param storage Storage object that communicate with local storage.
     */
    public void execute(Storage storage) {

        try {
            if (description.length() <= 5) {
                throw new DukeEmptyCommandException();
            }

            int index = Integer.parseInt(description.substring(5));

            if (index <= 0) {
                throw new DukeArrayOutOfBoundException();
            }
            TaskList.mark(index - 1);
            Ui.printTaskIsDone(index - 1);
        } catch (DukeException e) {
            Ui.printError(e.getMessage());
        } catch (NumberFormatException e) {
            Ui.printError("Index can only be Integer");
        }

    }
}
