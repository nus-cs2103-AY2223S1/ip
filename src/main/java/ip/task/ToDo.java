package ip.task;

import java.util.Scanner;

import ip.exception.NoDescription;

public class ToDo extends Task {
    
    public ToDo(Scanner options) throws NoDescription {
        if (options.hasNext()) {
            String description = options.nextLine().substring(1);
            super.describe(description);
            System.out.println("CREATED TASK: " + description);
        } else {
            throw new NoDescription();
        }
    }

    public ToDo(String[] props) {
        super.describe(props[2]);
        if (props[1].equals("1")) {
            super.mark();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
