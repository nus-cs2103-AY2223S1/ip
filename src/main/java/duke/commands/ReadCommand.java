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

    public void execute(Storage storage) {
        try {
            if (description.length() > 4) {
                throw new DukeTooManyArgumentException();
            }

            Ui.printRead();
        } catch (DukeException e) {
            Ui.printError(e.getMessage());
        }
    }
}
