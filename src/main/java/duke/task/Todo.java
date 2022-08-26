package duke.task;

import duke.exceptions.CorruptedLineException;
import duke.exceptions.EmptyDescriptionException;
import duke.util.ParsedData;

/**
 * Task that have no time tag.
 */
public class Todo extends Task {
    Todo(String description) {
        super(description);
    }

    /**
     * Factory method to create a Todo task from ParsedData
     * 
     * @param data ParsedData contains description of task
     * @return Todo
     * @throws EmptyDescriptionException Throws when the description is empty
     */
    public static Todo createTodo(ParsedData data) throws EmptyDescriptionException {
        if (data.description.length() == 0) {
            throw new EmptyDescriptionException("todo");
        }
        return new Todo(data.description);
    }

    /**
     * Factory method to create a Todo task from string.
     * 
     * @param description
     * @return Todo
     * @throws CorruptedLineException Throws when the description is empty
     */
    public static Todo createTodo(String description) throws CorruptedLineException {
        if (description.length() == 0)
            throw new CorruptedLineException();

        return new Todo(description);
    }

    /**
     * {@inheritDoc}
     * Adds [T] to identify as Todo task
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParsedData convertToParseData() {
        return new ParsedData(completed ? "Tc" : "Tx", description);
    }

}
