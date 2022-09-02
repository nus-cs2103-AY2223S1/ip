package duke;

public class Task {
    public static final String ICON_DONE = "X";
    public static final String ICON_UNDONE = " ";

    protected String details;
    private boolean isDone;

    /**
     * Creates a new Task.
     * @param details What needs to be done.
     */
    public Task(String details) {
        this.details = details;
        this.isDone = false;
    }

    /**
     * Mark a task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Mark a task as undone.
     */
    public void markUndone() {
        isDone = false;
    }

    /**
     * Checks is a task is done.
     * @return true if the task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Creates a Task from an encoded string.
     * @param s The encoded string.
     * @return The created task.
     */
    public static Task fromEncoded(String s) {
        String[] pieces = s.split("\\|");
        Task task;
        switch (pieces[0]) {
        case "T":
            task = new Todo(pieces[2]);
            break;
        case "D":
            task = new Deadline(pieces[2], pieces[3]);
            break;
        case "E":
            task = new Event(pieces[2], pieces[3]);
            break;
        default:
            task = new Task(pieces[2]);
            break;
        }
        if (pieces[1].equals(ICON_DONE)) {
            task.isDone = true;
        }

        return task;
    }

    /**
     * Gets an icon representing the state of the task.
     * @return An icon representing the state of the task.
     */
    public String getStatusIcon() {
        return isDone ? ICON_DONE : ICON_UNDONE;
    }

    /**
     * Gets an icon representing the type of the task.
     * @return An icon representing the type of the task.
     */
    public String getTaskIcon() {
        return " ";
    }

    /**
     * Gets the task details.
     * @return The task details.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Gets the task details in a format readable by {@link #fromEncoded(String) fromEncoded}.
     * @return The encoded task details.
     */
    public String getEncodedDetails() {
        return details;
    }

    /**
     * Gets the task in a format readable by {@link #fromEncoded(String) fromEncoded}.
     * @return The encoded task.
     */
    public String getEncoded() {
        return getTaskIcon() + "|" + getStatusIcon() + "|" + getEncodedDetails();
    }

    @Override
    public String toString() {
        return "[" + getTaskIcon() + "][" + getStatusIcon() + "] " + getDetails();
    }
}
