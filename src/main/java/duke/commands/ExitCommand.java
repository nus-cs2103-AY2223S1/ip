package duke.commands;

import duke.Storage;
import duke.Ui;

/**
 * Represents a <code>Command</code> to exit Duke.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a <code>ExitCommand</code>.
     *
     * @param description description of command.
     */
    public ExitCommand(String description) {
        super(description);
    }

    /**
     * Saves current tasks into local Storage and exit Duke.
     *
     * @param storage Storage object that communicate with local storage.
     */
    public String execute(Storage storage) {
        storage.save();
        return Ui.printExit();
        //System.exit(0);
    }
}
