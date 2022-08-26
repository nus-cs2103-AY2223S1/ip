package duke.task;

import duke.exceptions.CorruptedLineException;
import duke.exceptions.EmptyDescriptionException;
import duke.util.ParsedData;

public class Todo extends Task {
    Todo(String description) {
        super(description);
    }

    public static Todo createTodo(ParsedData data) throws EmptyDescriptionException {
        if (data.description.length() == 0) {
            throw new EmptyDescriptionException("todo");
        }
        return new Todo(data.description);
    }

   public static Todo createTodo(String description) throws CorruptedLineException {
        if (description.length() == 0)
            throw new CorruptedLineException();

        return new Todo(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public ParsedData convertToParseData() {
        return new ParsedData(completed ? "Tc" : "Tx", description);
    }


}
