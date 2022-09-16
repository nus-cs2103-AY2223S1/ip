package duke.models;

/**
 * A child class Todo that inherits properties description and isDone from Task
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Additional constructor for Todo class
     * @param description description of the Todo
     * @param isDone indicate if the Todo is done
     */
    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string representation of the {@code Todo} object
     * @return "T"
     */
    @Override
    public String getSymbol() {
        return "T";
    }

    /**
     * Returns the description of the {@code Todo} object
     * @return description as a string
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string representation of the {@code Todo} object
     * in a format that is convenient to save and load files
     * @return String to write to file
     */
    @Override
    public String stringToWrite() {
        return this.getSymbol() + "|" + (super.isDone ? "1" : "0") + "|" + this.getDescription();
    }

    public void postponeTask() {
        // do nothing
    }

    /**
     * Returns the string representation of the {@code Todo} object
     * @return string representation of the {@code Todo} object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
