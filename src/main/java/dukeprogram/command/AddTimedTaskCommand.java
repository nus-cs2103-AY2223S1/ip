package dukeprogram.command;

import java.util.Iterator;

import dukeprogram.Duke;
import exceptions.InvalidCommandException;
import utilities.StringUtilities;

/**
 * AddEventTaskCommand adds any timed type task to task list of the instance of Duke given.
 * An implementor that adds a TimedTask must inherit from this class to use this.
 */
public abstract class AddTimedTaskCommand extends Command {

    protected String taskName;
    protected String dateAndTime;

    private final String delimiter;
    private final String missingNameMessage;
    private final String missingDateAndTimeMessage;

    /**
     * Creates a new AddTimedTaskCommand
     * @param duke the instance of Duke that spawned this command
     */
    public AddTimedTaskCommand(Duke duke,
                               String delimiter, String missingNameMessage, String missingDateAndTimeMessage) {
        super(duke);
        this.delimiter = delimiter;
        this.missingNameMessage = missingNameMessage;
        this.missingDateAndTimeMessage = missingDateAndTimeMessage;
    }

    /**
     * Parses the elements given and adds a newly created event task type
     * @param elements the continued iterator of elements
     * @throws InvalidCommandException if there is not enough arguments given
     */
    @Override
    public void parse(Iterator<String> elements) throws InvalidCommandException {
        taskName = fetchName(elements);
        if (taskName.equals("")) {
            throw new InvalidCommandException(missingNameMessage);
        }

        dateAndTime = fetchDateAndTime(elements);
        if (dateAndTime.equals("")) {
            throw new InvalidCommandException(missingDateAndTimeMessage);
        }
    }

    private String fetchName(Iterator<String> elements) {
        StringBuilder sb = new StringBuilder();

        String next = elements.next();

        while (!next.equals(delimiter)) {
            sb.append(next);
            if (!elements.hasNext()) {
                break;
            }
            next = elements.next();
            if (!next.equals(delimiter)) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    private String fetchDateAndTime(Iterator<String> elements) {
        return StringUtilities.concatByDelimiter(elements, " ");
    }
}
