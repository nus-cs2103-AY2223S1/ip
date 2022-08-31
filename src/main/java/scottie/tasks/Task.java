package scottie.tasks;

/**
 * Encapsulates a task that the user wants to record.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a Task with the given description and isDone status.
     * This constructor is called by subclasses of Task.
     *
     * @param description The description of this Task.
     * @param isDone Whether this Task is done.
     */
    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a Task constructed based on the data in the provided string.
     * Based on the first character of the string, the corresponding
     * subclass' factory method is invoked to create the Task.
     *
     * @param encodedString The string containing the data for the Task.
     * @return The constructed Task.
     * @throws InvalidTaskDataException If the string is not formatted correctly.
     */
    static Task fromEncodedString(String encodedString) throws InvalidTaskDataException {
        char taskLetter = encodedString.charAt(0);
        TaskType taskType = TaskType.fromLetter(taskLetter);
        if (taskType == null) {
            throw new InvalidTaskDataException("Task type letter not recognised");
        }
        switch (taskType) {
        case TODO:
            return Todo.fromEncodedString(encodedString);
        case DEADLINE:
            return Deadline.fromEncodedString(encodedString);
        case EVENT:
            return Event.fromEncodedString(encodedString);
        default:
            // Any invalid task type should have been handled above by the null check.
            // Only way execution can reach here is if a new scottie.TaskType was added
            // but the switch statement was not updated.
            throw new RuntimeException(String.format("Missing Task subclass for TaskType with letter %c", taskLetter));
        }
    }

    /**
     * Marks this Task as done.
     */
    void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this Task as not done.
     */
    void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns whether this Task's description contains the
     * given searchText.
     * This matching is case-insensitive.
     *
     * @param searchText The text to match against the description.
     * @return Whether this Task's description contains the searchText.
     */
    boolean matchesAgainst(String searchText) {
        return this.description.toLowerCase().contains(searchText.toLowerCase());
    }

    /**
     * Returns an encoded string representation of this Task.
     * The encoded string can later be decoded to recover the
     * corresponding Task.
     *
     * @return The encoded string representation of this Task.
     */
    String toEncodedString() {
        return String.format("%d|%s", this.isDone ? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.description);
    }
}
