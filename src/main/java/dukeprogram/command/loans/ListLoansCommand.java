package dukeprogram.command.loans;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

import dukeprogram.Duke;
import dukeprogram.command.Command;
import dukeprogram.facilities.Loan;
import dukeprogram.userinterface.Widget;
import exceptions.IncompleteCommandException;
import exceptions.InvalidCommandException;

/**
 * ListLoansCommand can print the loans collection onto the GUO
 */
public class ListLoansCommand extends Command {

    /**
     * Creates a command
     *
     * @param duke the instance of duke this is associated to
     */
    public ListLoansCommand(Duke duke) {
        super(duke);
    }

    @Override
    public void parse(Iterator<String> elements) throws IncompleteCommandException, InvalidCommandException {
        printToGui();
    }

    /**
     * Sends a message to the window of the Duke instance to list
     * all the tasks to the GUI window
     */
    public void printToGui() {
        Loan[] loans = duke.getLoanCollection().getAllLoans();

        String formattedLoanCollectionString = Arrays.stream(loans)
                .map(Loan::toString).collect(Collectors.joining("\n"));

        double poolAmount = Arrays.stream(loans).mapToDouble(Loan::getAmount).sum();
        String debt;
        if (poolAmount >= 0) {
            debt = "\n\t\tPAYABLE $" + poolAmount;
        } else {
            debt = "\n\t\tRECEIVABLE $" + -poolAmount;
        }
        duke.sendMessage("Here are your loans:\n",
                new Widget(Arrays.stream(loans).map(Loan::makeWidget).collect(Collectors.toList())));
    }
}
