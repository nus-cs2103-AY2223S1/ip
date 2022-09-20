package duke.tasks;

/**
 * Tasks with only a description
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo with the given description
     *
     * @param description The task description
     */
    public Todo(String description) {
        super(description, 'T');
    }
}
