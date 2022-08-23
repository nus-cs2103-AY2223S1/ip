package duke;

/**
 * item used in Duke
 */
public class Task {
    protected boolean completed;
    protected String name;

    /**
     * constructor for new task
     * OUTDATED
     * @param name name of task
     * @throws MissingDescriptionException missing name
     */
    public Task(String name) throws MissingDescriptionException {
        if (name.equals("") || name.equals(" ")) {
            throw new MissingDescriptionException();
        } else {
            this.name = name;
            this.completed = false;
        }

    }

    /**
     * to mark task as completed
     */
    public void mark() {
        this.completed = true;
    }

    /**
     * to mark task as not completed
     */
    public void unmark() {
        this.completed = false;
    }

    /**
     * returns string representation of task.
     * @return string with type, completed or not, name, and date time(if applicable)
     */
    @Override
    public String toString() {
        String comp = this.completed
                ? "[X]"
                : "[ ]";
        return comp + name;
    }

    /**
     * return string representation of task to be written in text file
     * @return string representation to be written in text file
     */
    public String toData() {
        return this.toString();
    }
}
