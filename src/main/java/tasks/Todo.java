package tasks;

import util.ParsedData;

/**
 * This class encapsulates a Todo Task.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo
     *
     * @param parsedData The data used to create the Todo.
     */
    public Todo(ParsedData parsedData) {
        super(parsedData);
    }

    /**
     * Returns the type icon
     *
     * @return The type icon.
     */
    @Override
    public String getTypeIcon() {
        return "[T]";
    }

    /**
     * Returns the type letter
     *
     * @return The type letter.
     */
    @Override
    public String getTypeLetter() {
        return "T";
    }

    /**
     * Returns the time keyword
     *
     * @return The time keyword.
     */
    @Override
    public String getDuring() {
        return "";
    }

    /**
     * Returns the time text
     *
     * @return The time text.
     */
    @Override
    public String getTimeText() {
        return "";
    }

    @Override
    public String toString() {
        return this.getTypeIcon() + this.getStatusIcon() + this.description;
    }
}
