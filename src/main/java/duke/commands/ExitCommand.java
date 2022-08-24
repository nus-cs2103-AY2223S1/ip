package duke.commands;

import duke.Storage;
import duke.Ui;

public class ExitCommand extends Command{

    public ExitCommand(String description) {
        super(description);
    }

    public void execute(Storage storage) {
        storage.save();
        Ui.printExit();
        System.exit(0);
    }
}
