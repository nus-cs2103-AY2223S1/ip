package duke;

/**
 * item used in Duke.
 */
public class Task {
    protected static final String FIELD_DIVIDER = "/%&#@.,/";
    protected boolean isCompleted;
    protected String name;
    protected Priority priority;

    enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }

    /**
     * Constructor for new task.
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
            this.priority = Priority.MEDIUM;
        }

    }

    /**
     * Marks task as completed.
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Marks task as not completed.
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * Checks if task description contains keyword.
     *
     * @param keyword String to contain in description.
     * @return if keyword is in description of task.
     */
    public boolean contains(String keyword) {
        return this.name.contains(keyword);
    }

    /**
     * Sets the priority level of the task.
     *
     * @param priority the level user input.
     * @throws DukeException unknown priority level.
     */
    public void setPriority(String priority) throws DukeException {
        switch (priority) {
        case "low":
            this.priority = Priority.LOW;
            break;

        case "medium":
            this.priority = Priority.MEDIUM;
            break;

        case "high":
            this.priority = Priority.HIGH;
            break;

        default:
            throw new DukeException("Unknown priority level!");
        }
    }

    /**
     * Returns string representation of task.
     *
     * @return string with type, completed or not, name, and date time(if applicable).
     */
    @Override
    public String toString() {
        String comp = this.isCompleted
                ? "[X]"
                : "[ ]";
        return comp + name;
    }

    /**
     * Returns string representation of task to be written in text file.
     *
     * @return string representation to be written in text file.
     */
    public String toData() {
        return this.toString();
    }
}
