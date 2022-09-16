package duke;

/**
 * A Task has a name and keeps track of whether it has been done.
 */
public class Task {

    protected String name;
    protected boolean isDone;

    /**
     * Constructs a Task.
     *
     * @param name Name of task.
     * @param isDone Whether task is marked.
     * @throws DukeTaskException  If name of a task is empty.
     */
    protected Task(String name, boolean isDone) throws DukeTaskException {
        if (name.equals("") || name.equals(" ")) {
            throw new DukeTaskException("the name of a task can't be empty");
        }
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Marks the task.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks the task.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns string representing the task data.
     *
     * @return Representation of task.
     */
    public String saveString() {
        return name + "|" + isDone;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return Task as a string.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
