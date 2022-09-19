package duke;

/**
 * ToDo class to represent a new ToDo task.
 *
 * @author Sheryl Kong (A0240686Y)
 */

public class ToDo extends Task {

    /**
     * Constructor for ToDo class
     *
     * @param description description of the ToDo task
     * @param isDone indicates if the task is marked as done
     */

    public ToDo(String description, boolean isDone) {
        super(description.trim());
        this.isDone = isDone;
        Task.addTaskCount();
    }

    @Override
    public String toString() {
        return String.format("T | %s | %s", this.getStatusIcon(), this.description);
    }

}


