package duke.task;

import static duke.common.Constants.LINE_SEPARATOR;

/**
 * Parent class of all tasks
 */
public class Task {
    protected String description;
    protected boolean isDone;

    protected enum Type {
        DEADLINE,
        EVENT,
        TODO
    }

    protected Type type;

    /**
     * Initializes common elements of tasks
     *
     * @param description Description of a task
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns string representation of isDone field
     *
     * @return X for done, empty string otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns task as data in disk storage
     *
     * @return Data representation of task
     */
    public String toDataString() {
        return LINE_SEPARATOR
                + isDone
                + LINE_SEPARATOR
                + description
                + LINE_SEPARATOR;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
