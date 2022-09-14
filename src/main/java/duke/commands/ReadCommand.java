package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.DukeTooManyArgumentException;

/**
 * Represents a <code>Command</code> to read input.
 */
public class ReadCommand extends Command {

    /**
     * Constructs a <code>ReadCommand</code> object.
     *
     * @param description description of command
     */
    public ReadCommand(String description) {
        super(description);
    }

    /**
     * Read the current task in the tasklist.
     *
     * @param storage Storage object that communicate with local storage.
     */
    public String execute(Storage storage) {
        try {
            if (description.length() > 4) {
                throw new DukeTooManyArgumentException();
            }

            return Ui.printRead();
        } catch (DukeException e) {
            return Ui.printError(e.getMessage());
        }
    }
}
