package cheese.task;

public abstract class Task {
    /** Description of Task */
    private String description;

    /** Boolean representing whether Task is done or not */
    private boolean isDone;

    /**
     * Constructor to create a new Task
     * 
     * @param description description of Task
     */
    protected Task(String description) {
        this.description = description;
        isDone = false;
    }

    protected Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    public void setDone() {
        isDone = true;
    }

    public void setNotDone() {
        isDone = false;
    }

    public String toFileString() {
        String isDoneString = isDone ? "T" : "F";
        return isDoneString + " // " + description;
    }

    /**
     * Returns string representation of Task
     * 
     * @return string representation of Task
     */
    @Override
    public String toString() {
        String checkbox = this.isDone ? "[X] " : "[ ] ";
        return checkbox + this.description;
    }
}
