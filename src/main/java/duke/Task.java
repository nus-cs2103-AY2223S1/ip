package duke;

import duke.exception.DukeInvalidSaveDataException;

/**
 * Represents a task to be completed. Has a description,
 * and can be marked as done or undone.
 */
public class Task {
    private static final char DEFAULT_TAG = 'T';
    private static final char DONE_SYMBOL = 'X';

    private boolean isDone;
    private String description;
    private char tag;

    /**
     * Creates a new todo with the given description and done status.
     *
     * @param description
     * @param tag
     * @param isDone
     */
    public Task(String description, char tag, boolean isDone) {
        this.description = description;
        this.tag = tag;
        this.isDone = isDone;
    }

    public Task(String description) {
        this(description, false);
    }

    private Task(String description, boolean isDone) {
        this(description, DEFAULT_TAG, isDone);
    }

    /**
     * Converts save string data to a Task object.
     * The save string data is in the format:
     * <p>
     * <pre>
     * T,"&lt;description&gt;"
     * </pre>
     * <p>
     *
     * @param saveString the save string data
     * @return the new Task object created from saveString
     * @throws DukeInvalidSaveDataException
     */
    public static Task fromSaveString(String saveString) throws DukeInvalidSaveDataException {
        String[] splitSaveString = saveString.split("(\",\")|(\",)|(,\")|\"");
        if (splitSaveString.length != 2) {
            throw new DukeInvalidSaveDataException();
        }
        assert splitSaveString[0].equals("T") : "Save data is not a todo.";
        assert splitSaveString[0].endsWith("1") || splitSaveString[0].endsWith("0")
                : "Save data contains invalid isDone value.";
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

    /**
     * Returns true if the description of task contains the given string.
     * @param search given search string
     * @return whether search string is found in description
     */
    public boolean descriptionContains(String search) {
        return this.description.contains(search);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.tag, this.isDone ? DONE_SYMBOL : ' ', this.description);
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
        return isDone == task.isDone
                && description.equals(task.description)
                && tag == task.tag;
    }
}
