public abstract class Task {
    private final String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public static Task fromEncodedString(String encodedString) throws InvalidTaskDataException {
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
            // Only way execution can reach here is if a new TaskType was added
            // but the switch statement was not updated.
            throw new RuntimeException(String.format("Missing Task subclass for TaskType with letter %c", taskLetter));
        }
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String toEncodedString() {
        return String.format("%d|%s", this.isDone ? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "✔" : " ", this.description);
    }
}
