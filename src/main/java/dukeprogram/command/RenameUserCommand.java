package dukeprogram.command;

import java.util.Iterator;

import dukeprogram.Duke;
import dukeprogram.storage.SaveManager;
import exceptions.IncompleteCommandException;
import utilities.StringUtilities;

/**
 * RenameUserCommand is used to rename the user name
 */
public class RenameUserCommand extends Command {

    /**
     * Creates a command
     *
     * @param duke the instance of duke this is associated to
     */
    public RenameUserCommand(Duke duke) {
        super(duke);
    }

    @Override
    public void parse(Iterator<String> elements) throws IncompleteCommandException {
        if (!elements.hasNext()) {
            throw new IncompleteCommandException("You need to specify your new name.");
        }

        String name = StringUtilities.concatByDelimiter(elements, " ");

        duke.getUser().setName(name);
        SaveManager.save("user", duke.getUser());
        duke.sendMessage(String.format("Alright, I'll call you %s from now on", name));
    }
}
