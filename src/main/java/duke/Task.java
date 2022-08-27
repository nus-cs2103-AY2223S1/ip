package duke;

/**
 * Represents a task to be completed. Has a description,
 * and can be marked as done or undone.
 */
public class Task {
    private boolean done;
    private String description;
    private char tag;

    public Task(String description, char tag, boolean done) {
        this.description = description;
        this.tag = tag;
        this.done = done;
    }

    public Task(String description) {
        this(description,false);
    }

    private Task(String description, boolean done) {
        this(description, 'T', done);
    }

    /**
     * Converts save string data to a Task object.
     * The save string data is in the format:
     * <p>
     * <pre>
     * T,"<description>"
     * </pre>
     * <p>
     * 
     * @param saveString the save string data
     * @return the new Task object created from saveString
     * @throws DukeException
     */
    public static Task fromSaveString(String saveString) throws DukeException {
        String[] splitSaveString = saveString.split("(\",\")|(\",)|(,\")|\"");
        if(splitSaveString.length != 2) {
            throw new DukeException("Tried to read unexpected save data.");
        }
        String description = splitSaveString[1];
        boolean done = splitSaveString[0].endsWith("1");
        return new Task(description, done);
    }

    /**
     * Converts Task object into save string data.
     * @return save string data that represents the Task object.
     */
    public String toSaveData() {
        return String.format("%s,%s,\"%s\"", this.tag, this.done ? '1' : '0', this.description);
    }

    /**
     * Mark a Task as done.
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Mark a Task as undone.
     */
    public void unmarkDone() {
        this.done = false;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.tag, this.done ? 'X' : ' ', this.description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o instanceof Task == false) {
            return false;
        }
        Task task = (Task) o;
        return done == task.done &&
                description.equals(task.description) &&
                tag == task.tag;
    }
}
