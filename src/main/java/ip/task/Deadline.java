package ip.task;

import java.util.Scanner;

import ip.exception.NoDeadline;
import ip.exception.NoDescription;

public class Deadline extends Task {
    private String deadline;
    
    public Deadline(Scanner options) throws NoDescription, NoDeadline {
        if (options.hasNext()) {
            options.useDelimiter(" /by ");
            String description = options.next().substring(1);
            if (options.hasNext()) {
                String deadline = options.next();
                super.describe(description);
                this.deadline = deadline;
                System.out.println("CREATED DEADLINE: " + description + " DUE ON: " + deadline);
            } else {
                throw new NoDeadline();
            }
        } else {
            throw new NoDescription();
        }
    }

    public Deadline(String[] props) {
        super.describe(props[2]);
        this.deadline = props[3];
        if (props[1].equals("true")) {
            super.mark();
        }
    }

    public String writeFormat() {
        return "d|" + isComplete + "|" + description + "|" + deadline + "|\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (Deadline: " + deadline + ")";
    }
}
