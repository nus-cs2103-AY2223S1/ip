/**
 * A Todo is a Task without an associated date/time.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo object with a given description and whether it has been done.
     *
     * @param description the description of the task
     * @param isDone      whether the task is marked as done
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * The task type code of a Todo object is T. Hence, this method returns T.
     *
     * @return "T"
     */
    @Override
    public String getTaskTypeCode() {
        return "T";
    }
}
