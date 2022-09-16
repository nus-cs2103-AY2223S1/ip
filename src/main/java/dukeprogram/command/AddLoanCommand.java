package dukeprogram.command;

import java.util.Iterator;
import java.util.NoSuchElementException;

import dukeprogram.Duke;
import exceptions.IncompleteCommandException;
import exceptions.InvalidCommandException;

/**
 * AddLoanCommand can add loans to the Loan Collection of the given duke instance
 */
public class AddLoanCommand extends Command {

    private static final String DELIMITER = "-amount";

    /**
     * Creates a AddLoanCommand
     *
     * @param duke the instance of duke this is associated to
     */
    public AddLoanCommand(Duke duke) {
        super(duke);
    }

    @Override
    public void parse(Iterator<String> elements)
            throws IncompleteCommandException, InvalidCommandException {
        if (!elements.hasNext()) {
            duke.sendMessage("Hmm, you need to tell me what you want to add...");
            throw new IncompleteCommandException("Usage: add <person_name> -amount <amount_owed>");
        }

        String personName = fetchName(elements);
        if (personName.equals("")) {
            throw new InvalidCommandException("You need to specify the name of the creditor");
        }

        double amountOwed;
        try {
            String element = elements.next();
            if (element.charAt(0) == '$') {
                element = element.substring(1);
            }
            amountOwed = Double.parseDouble(element);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("The amount owed must be specified in numerics only");
        } catch (NullPointerException | NoSuchElementException e) {
            throw new InvalidCommandException("You need to specify the amount of money owed");
        }

        duke.getLoanCollection().add(personName, amountOwed);
        duke.save();
    }

    private String fetchName(Iterator<String> elements) {
        StringBuilder sb = new StringBuilder();

        String next = elements.next();

        while (!next.equals(DELIMITER)) {
            sb.append(next);
            if (!elements.hasNext()) {
                break;
            }
            next = elements.next();
            if (!next.equals(DELIMITER)) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }
}
