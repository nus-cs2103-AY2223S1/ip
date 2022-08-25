package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.DukeTooManyArgumentException;

public class ReadCommand extends Command {

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
