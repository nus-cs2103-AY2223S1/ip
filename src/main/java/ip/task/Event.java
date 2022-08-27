package ip.task;

import java.util.Scanner;

import ip.exception.NoDescription;
import ip.exception.NoPeriod;

public class Event extends Task {
    
    private String period;
    
    public Event(Scanner options) throws NoDescription, NoPeriod {
        if (options.hasNext()) {
            options.useDelimiter(" /at ");
            String description = options.next().substring(1);
            if (options.hasNext()) {
                String period = options.next();
                super.describe(description);
                this.period = period;
                System.out.println("CREATED EVENT: " + description + " AT: " + period);
            } else {
                throw new NoPeriod();
            }
        } else {
            throw new NoDescription();
        }
    }

    public Event(String[] props) {
        super.describe(props[2]);
        this.period = props[3];
        if (props[1].equals("true")) {
            super.mark();
        }
    }

    public String writeFormat() {
        return "e|" + isComplete + "|" + description + "|" + period + "|\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (At: " + period + ")";
    }
}
