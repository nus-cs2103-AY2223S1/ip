package dukeprogram.command.loans;

import java.util.Iterator;

import dukeprogram.Duke;
import dukeprogram.Main;
import dukeprogram.command.Command;
import dukeprogram.userinterface.Widget;
import dukeprogram.userinterface.WidgetButton;
import exceptions.IncompleteCommandException;
import exceptions.InvalidCommandException;

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

        case "delete":
            new DeleteLoanCommand(duke).parse(elements);
            break;

        case "help":
            printHelp();
            break;

        default:
            throw new InvalidCommandException(String.format("I'm cannot perform %s", thisElement));
        }
    }

    private void printHelp() {
        duke.sendMessage("Here are the commands you can specify:\n"
                + "\nlist | add | delete");
        duke.sendMessage("For example, you can do \"loans list\" to list all current loans.");
        duke.sendMessage("Don't forget you can also specify the help command after any argument"
                        + "to learn what you can do!",
                new Widget(new WidgetButton("User Guide [Loans]",
                        e -> Main.getPrimaryHostService()
                                .showDocument("https://rui-han-crh.github.io/ip/#loans"))));
    }
}
