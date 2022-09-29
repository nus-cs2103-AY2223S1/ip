package duke.tasks;

/*
 * Represents a Todo task.
 */
public class Todo extends Task {
    /*
     * Constructs a Todo object.
     * 
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /*
     * Constructs a Todo object.
     * 
     * @param description The description of the task.
     * 
     * @param isDone The status of the task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /*
     * Returns the string representation of the Todo object.
     * 
     * @return The string representation of the Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /*
     * Returns the string representation of the Todo object to be saved.
     * 
     * @return The string representation of the Todo object to be saved.
     */
    @Override
    public String save() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
