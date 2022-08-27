package ip.task;

import ip.exception.MissingDescription;

import java.util.Scanner;

public class ToDo extends Task {
    
    public ToDo(Scanner options) throws MissingDescription {
        if (options.hasNext()) {
            String description = options.nextLine().substring(1);
            super.describe(description);
            System.out.println("CREATED TASK: " + description);
        } else {
            throw new MissingDescription();
        }
    }

    public ToDo(String[] props) {
        super.describe(props[2]);
        if (props[1].equals("true")) {
            super.mark();
        }
    }

    public String writeFormat() {
        return "t|" + isComplete + "|" + description + "||\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
