package duke.task;

/**
 * Child class of Task with an error
 */
public class ErrorTask extends Task {
    /**
     * Constructor for the errortask with description
     */
    public ErrorTask(String description) {
        super(description, "[]");
    }
}
