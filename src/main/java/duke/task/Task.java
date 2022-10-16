package duke.task;

/**
 * Class for Duke Tasks.
 */
public class Task {
    protected String name;
    protected String note;
    private boolean isCompleted;

    /**
     * Constructor for the Task class.
     * @param name Input description of the task
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Gets the completion status of the Task
     * @return A status icon if its completed/uncompleted
     */
    public String getStatus() {
        return isCompleted ? "X" : " ";
    }

    /**
     * Gets icon or note description of the Task.
     * @return An icon or note description of Task.
     */
    public String getNote() {
        return note == null ? "-" : note;
    }
    /**
     * Marks the task to show completed
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Unmarks the task to show uncompleted
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * Returns a string representation of the Task object.
     * @return String representation of the Task object.
     */
    @Override
    public String toString() {
        if (note == null) {
            return String.format("[%s] %s", getStatus(), name);
        } else {
            return String.format("[%s] %s [Note: %s]", getStatus(), name, note);
        }
    }

    /**
     * Returns the format of Task object in format to be saved.
     * @return String of Task object to be saved.
     */
    public String changeFormat() {
        if (note == null) {
            return String.format("[%s] | %s", getStatus(), name);
        } else {
            return String.format("[%s] | %s [Note: %s]", getStatus(), name, note);
        }
    };

    /**
     * Adds a note to Task object.
     * @param note String note to be added.
     */
    public void addNote(String note) {
        this.note = note;
    }
}
