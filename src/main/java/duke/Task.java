package duke;

<<<<<<< Updated upstream
=======
/**
 * Represents a Task with description, boolean to set the Task as done or not done, and type (ToDo = 'T',
 * Deadline = 'D', Event = 'E').
 */
>>>>>>> Stashed changes
public class Task {
    protected String description;
    protected boolean isDone;
    protected char type;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public Task(String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markNotDone() {
        isDone = false;
    }

    public char getStatusIcon() {
        return isDone ? 'X' : ' '; // done = X
    }

    @Override
    public String toString() {
        return String.format("[%c][%c] %s", type, getStatusIcon(), description);
    }

<<<<<<< Updated upstream
    public String data() {
=======
    /**
     * Returns the String representation of the Task for Storage.
     *
     * @return String representation of the Task for Storage.
     */
    public String toData() {
>>>>>>> Stashed changes
        return String.format("%c, %c, %s", type,  getStatusIcon(), description);
    }
}
