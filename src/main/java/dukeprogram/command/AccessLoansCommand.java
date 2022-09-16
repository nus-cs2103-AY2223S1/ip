package dukeprogram.command;

import dukeprogram.Duke;
import exceptions.IncompleteCommandException;
import exceptions.InvalidCommandException;

import java.util.Iterator;

/**
 * AccessLoansCommand allows management of loans with use of the LoansCollection object
 */
public class AccessLoansCommand extends Command {

    /**
     * Creates a command
     *
     * @param duke the instance of duke this is associated to
     */
    public AccessLoansCommand(Duke duke) {
        super(duke);
    }

    @Override
    public void parse(Iterator<String> elements)
            throws IncompleteCommandException, InvalidCommandException {
        if (!elements.hasNext()) {
            throw new IncompleteCommandException(
                    "You should specify what loan management procedure you want to perform"
            );
        }

        String thisElement = elements.next();

        switch (thisElement) {
        case "list":
            new ListLoansCommand(duke).printToGui();
            break;

        /*case "find":
            new FindLoanCommand(duke).parse(elements);
            break;*/

        case "add":
            new AddLoanCommand(duke).parse(elements);
            break;

        /*case "delete":
            new DeleteLoanCommand(duke).parse(elements);
            break;*/

        default:
            throw new InvalidCommandException(String.format("I'm cannot perform %s", thisElement));
        }
    }
}
