package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeArrayOutOfBoundException;
import duke.exceptions.DukeEmptyCommandException;
import duke.exceptions.DukeException;

public class UnMarkCommand extends Command {

    public UnMarkCommand(String description) {
        super(description);
    }

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
