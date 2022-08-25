package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeEmptyCommandException;
import duke.exceptions.DukeEmptyDescriptionException;

public class FindCommand extends Command{

    public FindCommand(String description) {
        super(description);
    }

    @Override
    public void execute(Storage storage) {
        try {
            if (description.length() <= 5) {
                throw new DukeEmptyCommandException();
            }

            String prefix = description.substring(5);
            Ui.printFind(prefix);
        } catch (DukeEmptyCommandException e) {
            Ui.printError(e.getMessage());
        }
    }
}


