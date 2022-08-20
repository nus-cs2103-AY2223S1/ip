package duke.Task;
import duke.Exceptions.*;

/**
 * Todos are tasks without any date/time attached to it e.g., visit new theme park
 */
public class Todo extends Task {
    public Todo(String title) {
        super(title);
    }

    public Todo(String title, boolean completed) {
        super(title, completed);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveString() {
        return "T," + super.toSaveString();
    }

    public static Todo parse(String data) throws ParsingTaskException {
        String[] components = data.split(",");
        if (components.length != 3) {
            throw new ParsingTaskException(String.format("Todos require 3 components, but found %d.", components.length));
        }
        try {
            boolean completed = Integer.parseInt(components[1]) == 1;
            String title = components[2];

            return new Todo(title, completed);
        } catch (NumberFormatException e) {
            throw new ParsingTaskException(String.format("Expected a number at component 1, but found %s", components[1]));
        }
    }
}