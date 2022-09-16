package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor that initializes a task object.
     * @param description short description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;

    }


    /**
     * Method to return date of a particular task.
     * @return String representation of date
     */
    public String date() {
        return "";
    }

    /**
     * Method to return timing of a particular task.
     * @return String representation of the task timing
     */
    public String timing() {
        return "";
    }

    /**
     * tells us if a particular task is done or not.
     * @return cross represents done, otherwise blank space means not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : "O"); // mark done task with X
    }

    /**
     * marks a specific task object as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * marks a specific task object as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * returns String representation.
     * @return String representation of Task
     */
    public String toString() {
        return this.description;
    }

}