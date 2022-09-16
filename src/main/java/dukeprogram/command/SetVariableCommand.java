package dukeprogram.command;

import dukeprogram.Duke;
import exceptions.IncompleteCommandException;
import exceptions.InvalidCommandException;

import java.util.Iterator;

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

        default:
            throw new InvalidCommandException(
                    String.format("Sorry, I'm unable to set the %s variable", element));
        }
    }
}
