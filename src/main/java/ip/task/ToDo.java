package ip.task;

import java.util.Scanner;

import ip.exception.MissingDescription;

/**
 * Encapsulation of a todo.
 */
public class ToDo extends Task {

    /**
     * Constructor to create a todo object.
     *
     * @param options Contains the description and deadline of the task.
     * @throws MissingDescription If there is no description in options.
     */
    public ToDo(Scanner options) throws MissingDescription {
        if (options.hasNext()) {
            String description = options.nextLine().substring(1);
            super.describe(description);
            System.out.println("CREATED TASK: " + description);
        } else {
            throw new MissingDescription();
        }
    }

    /**
     * Constructor to create deadline object from formatted string.
     *
     * @param props Contains data used to build the todo object.
     */
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
    public boolean hasString(String s) {
        return super.description.contains(s);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
