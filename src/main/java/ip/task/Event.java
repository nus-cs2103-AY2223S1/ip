package ip.task;

import ip.exception.BadTimespan;
import ip.exception.MissingDescription;

import java.util.Scanner;

public class Event extends Task {
    
    private String timespan;
    
    public Event(Scanner options) throws MissingDescription, BadTimespan {
        if (options.hasNext()) {
            options.useDelimiter(" /at ");
            String description = options.next().substring(1);
            if (options.hasNext()) {
                String timespan = options.next();
                super.describe(description);
                this.timespan= timespan;
                System.out.println("CREATED EVENT: " + description + " AT: " + timespan);
            } else {
                throw new BadTimespan("");
            }
        } else {
            throw new MissingDescription();
        }
    }

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
    public String toString() {
        return "[E]" + super.toString() + " (At: " + timespan + ")";
    }
}
