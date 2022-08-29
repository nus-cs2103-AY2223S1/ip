package duke;

/**
 * A task with title, done status and type.
 */
public class Task {

    private boolean done;
    private String title;
    private Types type;
    private enum Types { TODO, DEADLINE, EVENT }

    /**
     * Constructs a task object, given its title, type and completion status.
     *
     * @param title The title of the task.
     * @param type The type of the task - todo, deadline, event.
     * @param done The completion status of the task.
     */
    public Task(String title, String type, boolean done) {
        switch(type) {
        case "todo":
            this.type = Types.TODO;
            break;

        case "deadline":
            this.type = Types.DEADLINE;
            break;

        case "event":
            this.type = Types.EVENT;
            break;

        default:
            break;
        }
        this.done = done;
        this.title = title;
    }

    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + (isDone() ? "X" : " ") + "] " + title;
    }

    /**
     * Marks the task as being done.
     * Changing the boolean done to true.
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Marks the task as not done.
     * Changing the boolean done to false.
     */
    public void unmark() {
        this.done = false;
    }

    /**
     * Returns whether the task has been completed.
     *
     * @return boolean If the task has been completed
     */
    public boolean isDone() {
        return this.done;
    }

    /**
     * Retrieves the type of the task.
     *
     * @return String Type of the task.
     */
    public String getType() {
        switch(this.type) {
        case TODO:
            return "T";

        case DEADLINE:
            return "D";

        case EVENT:
            return "E";

        default:
            return "fail";

        }
    }

    /**
     * Returns the title of the task.
     *
     * @return The title of the task.
     */
    public String getTitle() {
        return title;
    }
}
