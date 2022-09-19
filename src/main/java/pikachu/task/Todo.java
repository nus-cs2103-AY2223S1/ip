package pikachu.task;

/**
 * Represents a task type. A <code>To do</code> object corresponds to
 * a task
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the name of to do "T"
     * @return "T" to represent to do
     */
    public String getName() {
        return "T";
    }

    /**
     * Returns the date of the to do
     * @return empty string as to do does not have time
     */
    public String getTiming() {
        return "";
    }

    /**
     * Returns the string of the to do when writing into task list
     * @return written format of to do in task list
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
