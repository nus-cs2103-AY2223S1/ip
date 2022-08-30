package ip.task;

import java.util.Scanner;

import ip.exception.BadTimespan;
import ip.exception.MissingDescription;

/**
 * Encapsulation of an event.
 */
public class Event extends Task {
    /** Timespan of the event. */
    private String timespan;

    /**
     * Constructor to create an event object.
     *
     * @param options Contains the description and timespan of the event.
     * @throws MissingDescription If there is no description in options.
     * @throws BadTimespan If the timespan is missing.
     */
    public Event(Scanner options) throws MissingDescription, BadTimespan {
        if (options.hasNext()) {
            options.useDelimiter(" /at ");
            String description = options.next().substring(1);
            if (options.hasNext()) {
                String timespan = options.next();
                super.describe(description);
                this.timespan = timespan;
                System.out.println("CREATED EVENT: " + description + " AT: " + timespan);
            } else {
                throw new BadTimespan("");
            }
        } else {
            throw new MissingDescription();
        }
    }

    /**
     * Constructor to create event object from formatted string.
     *
     * @param props Contains data used to build the event object.
     */
    public Event(String[] props) {
        super.describe(props[2]);
        this.timespan = props[3];
        if (props[1].equals("true")) {
            super.mark();
        }
    }

    public String writeFormat() {
        return "e|" + isComplete + "|" + description + "|" + timespan + "|\n";
    }

    @Override
    public boolean hasString(String s) {
        return super.description.contains(s);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (At: " + timespan + ")";
    }
}
