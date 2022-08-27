package duke;

/**
 * Represents a task to be completed. Has a description,
 * and can be marked as done or undone.
 */
public class Task {
    private boolean isDone;
    private String description;
    private char tag;

    public Task(String description, char tag, boolean isDone) {
        this.description = description;
        this.tag = tag;
        this.isDone = isDone;
    }

    public Task(String description) {
        this(description,false);
    }

    private Task(String description, boolean isDone) {
        this(description, 'T', isDone);
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
        boolean isDone = splitSaveString[0].endsWith("1");
        return new Task(description, isDone);
    }

    /**
     * Converts Task object into save string data.
     * @return save string data that represents the Task object.
     */
    public String toSaveData() {
        return String.format("%s,%s,\"%s\"", this.tag, this.isDone ? '1' : '0', this.description);
    }

    /**
     * Mark a Task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark a Task as undone.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    public boolean descriptionContains(String search) {
        return this.description.contains(search);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.tag, this.isDone ? 'X' : ' ', this.description);
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
        return isDone == task.isDone &&
                description.equals(task.description) &&
                tag == task.tag;
    }
}
