package duke;

/**
 * item used in Duke
 */
public class Task {
    protected boolean isCompleted;
    protected String name;

    /**
     * Constructor for new task
     * outdated.
     *
     * @param name name of task.
     * @throws MissingDescriptionException missing name.
     */
    public Task(String name) throws MissingDescriptionException {
        if (name.equals("") || name.equals(" ")) {
            throw new MissingDescriptionException();
        } else {
            this.name = name;
            this.isCompleted = false;
        }

    }

    /**
     * Mark task as completed.
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Mark task as not completed.
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * checks if task description contains keyword.
     *
     * @param keyword String to contain in description.
     * @return if keyword is in description of task.
     */
    public boolean contains(String keyword) {
        return this.toString().contains(keyword);
    }

    /**
     * Returns string representation of task.
     *
     * @return string with type, completed or not, name, and date time(if applicable)
     */
    @Override
    public String toString() {
        String comp = this.isCompleted
                ? "[X]"
                : "[ ]";
        return comp + name;
    }

    /**
     * Return string representation of task to be written in text file
     *
     * @return string representation to be written in text file
     */
    public String toData() {
        return this.toString();
    }
}
