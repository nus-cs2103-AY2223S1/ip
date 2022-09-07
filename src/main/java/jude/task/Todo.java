package jude.task;

/**
 * A Todo is a Task without an associated date/time.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo object with a given description and whether it has been done.
     *
     * @param description The description of the task.
     * @param isDone      Whether the task is marked as done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the task type code for a {@code Todo} object, i.e. "T".
     *
     * @return "T".
     */
    @Override
    public String getTaskTypeCode() {
        return "T";
    }

    /**
     * Returns false because {@code Todo} objects do not require reminders.
     *
     * @param seconds Number of seconds time notice required.
     * @return False
     */
    @Override
    public boolean needsReminder(long seconds) {
        return false;
    }
}
