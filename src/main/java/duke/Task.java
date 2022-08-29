package duke;

/**
 * Abstract Task class which can be represented in the form of ToDo, Event and DeadLine.
 * Stores the name and status of the event.
 */
public abstract class Task {
    private String name = null;
    private boolean isDone = false;

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Adds the name of the task.
     * @param name the description of the task
     * @throws DukeException
     */
    public void addName(String name) throws DukeException {
        this.name = name;
    }

    /**
     * Returns the description and the current status of the task for displaying purposes.
     * @return the description and status of the task
     */
    public String getTask() {
        return String.format("%c | %s", (this.isDone == true ? '1' : '0'), this.name);
    }

    /**
     * Returns the description and the current status of the task for storing purposes.
     * @return the description and status of the task
     */
    public String getStatus() {
        return String.format("[%c] %s", (this.isDone == true ? 'X' : ' '), this.name);
    }

}
