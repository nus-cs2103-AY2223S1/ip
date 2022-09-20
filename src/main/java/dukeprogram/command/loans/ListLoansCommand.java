package dukeprogram.command.loans;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import dukeprogram.Duke;
import dukeprogram.command.Command;
import dukeprogram.facilities.Loan;
import dukeprogram.userinterface.Widget;
import dukeprogram.userinterface.WidgetLoanLabel;
import exceptions.IncompleteCommandException;
import exceptions.InvalidCommandException;
import javafx.scene.layout.Region;

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

        double poolAmount = Arrays.stream(loans).mapToDouble(Loan::getAmount).sum();
        List<Region> loansWidgets = Arrays.stream(loans).map(Loan::makeWidget)
                .collect(Collectors.toList());

        WidgetLoanLabel debt;
        if (poolAmount >= 0) {
            debt = new WidgetLoanLabel("TOTAL PAYABLE", poolAmount);
        } else {
            debt = new WidgetLoanLabel("TOTAL RECEIVABLE", poolAmount);
        }
        Region space = new Region();
        space.setMinHeight(10);
        loansWidgets.add(space);
        loansWidgets.add(debt);

        duke.sendMessage("Here are your loans:\n",
                new Widget(loansWidgets));
    }
}
