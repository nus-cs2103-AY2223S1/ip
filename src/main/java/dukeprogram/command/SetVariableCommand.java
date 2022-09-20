package dukeprogram.command;

import java.util.Iterator;

import dukeprogram.Duke;
import dukeprogram.command.customisations.ChangeProfilePictureCommand;
import dukeprogram.command.customisations.RenameUserCommand;
import exceptions.IncompleteCommandException;
import exceptions.InvalidCommandException;

/**
 * SetVariableCommand is used to set a variable related to the currently running Duke task.
 * Variables that can be set include things like names, etc.
 */
public class SetVariableCommand extends Command {
    /**
     * Creates a command
     *
     * @param duke the instance of duke this is associated to
     */
    public SetVariableCommand(Duke duke) {
        super(duke);
    }

    @Override
    public void parse(Iterator<String> elements)
            throws IncompleteCommandException, InvalidCommandException {
        if (!elements.hasNext()) {
            throw new IncompleteCommandException("You need to specify what variable you want to set!");
        }

        String element = elements.next();

        switch (element) {
        case "name":
            new RenameUserCommand(duke).parse(elements);
            break;

        case "picture":
            new ChangeProfilePictureCommand(duke).parse(elements);
            break;

        default:
            throw new InvalidCommandException(
                    String.format("Sorry, I'm unable to set the %s variable", element));
        }
    }
}
