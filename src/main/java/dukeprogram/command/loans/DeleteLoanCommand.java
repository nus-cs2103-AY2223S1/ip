package dukeprogram.command.loans;

import java.util.Iterator;

import dukeprogram.Duke;
import dukeprogram.command.Command;
import dukeprogram.command.ContinuableCommand;
import dukeprogram.facilities.Loan;
import exceptions.IncompleteCommandException;
import exceptions.InvalidCommandException;
import utilities.StringUtilities;

/**
 * DeleteLoanCommand can delete the loan record of a person, within the LoanCollection
 * of the Duke instance which spawned the command
 */
public class DeleteLoanCommand extends Command implements ContinuableCommand {
    /**
     * Creates a command
     *
     * @param duke the instance of duke this is associated to
     */
    public DeleteLoanCommand(Duke duke) {
        super(duke);
    }

    @Override
    public void parse(Iterator<String> elements) throws IncompleteCommandException, InvalidCommandException {
        if (!elements.hasNext()) {
            duke.sendMessage("You have to specify the name of the creditor that you want to clear records of.");
        }

        String thisElement = StringUtilities.concatByDelimiter(elements, " ");

        if (thisElement.equals("all")) {
            askToDeleteAllLoans();
        } else if (duke.getLoanCollection().containsKey(thisElement)) {
            Loan loan = duke.getLoanCollection().remove(thisElement);
            duke.sendMessage("Okay, I've removed the loan records of " + loan.getCreditorName());
            duke.save();
        } else {
            duke.sendMessage("Sorry, I wasn't able to find a person called "
                    + thisElement + " in the loan records!");
        }
    }

    @Override
    public void continueParse(Iterator<String> elements) throws InvalidCommandException {
        if (!elements.hasNext()) {
            throw new InvalidCommandException("Nothing was given.");
        }

        String thisElement = elements.next();

        switch (thisElement.toLowerCase()) {
        case "y":
        case "yes":
            duke.getLoanCollection().clear();
            duke.sendMessage("Okay, I've cleared the loan records of everyone.");
            break;

        case "n":
        case "no":
            duke.sendMessage("I'll leave the loans as is");
            break;

        default:
            throw new InvalidCommandException("The previous command was ignored.");
        }

        duke.save();
    }

    private void askToDeleteAllLoans() {
        duke.attachState(this);
        duke.sendMessage("Are you sure you want to delete loan records for everyone?\n"
                + "\nThis effectively makes the resets the whole loan collection.");
    }
}
