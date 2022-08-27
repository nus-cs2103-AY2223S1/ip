package cheese.task;

public class Task {
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

    /** Marks this task as done */
    public void markAsDone() {
        isDone = true;
    }

    /** Marks this task as not done */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Checks if task description contains given keyword.
     * 
     * @param keyword Keyword to search for.
     * @return True, if description contains given keyword. False otherwise.
     */
    public boolean hasKeyword(String keyword) {
        return description.contains(keyword);
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
