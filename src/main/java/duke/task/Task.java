package duke.task;

/**
 * Abstract class representing tasks that the user needs to complete.
 *
 * @author ish1506
 */
public abstract class Task {
    private final String name;
    private boolean isDone = false;

    /**
     * Constructs a <code>Task</code>.
     * Tasks have an incomplete status by default.
     *
     * @param name the name of the task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Constructs a <code>Task</code>.
     *
     * @param name   the name of the task.
     * @param isDone the status of the task.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Marks the <code>Task</code> as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the <code>Task</code> as undone.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Checks if <code>Task</code> name contains keyword.
     *
     * @param keyword the keyword to check.
     * @return true if <code>keyword</code> is found in name.
     */
    public boolean isNameContaining(String keyword) {
        return name.contains(keyword);
    }

    /**
     * Returns a concrete <code>Task</code> instance from its string representation.
     *
     * @param inputString the string representation of the <code>Task</code>.
     * @return the <code>Task</code> instance.
     */
    public static Task fromString(String inputString) {
        char typeOfTask = inputString.charAt(1);
        switch (typeOfTask) {
        case 'T':
            return Todo.fromString(inputString);
        case 'D':
            return Deadline.fromString(inputString);
        case 'E':
            return Event.fromString(inputString);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String icon = this.isDone ? "[X] " : "[ ] ";
        return icon + this.name;
    }
}
