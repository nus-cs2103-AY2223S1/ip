package entities;

public class Task {
    private Boolean isComplete = false;
    private String description;

    /**
     * Sets the description of the task
     * 
     * @param desc Description of the task
     */
    public Task(String desc) {
        this.description = desc;
    }

    /**
     * Flips the value isComplete
     */
    public void toggleComplete() {
        isComplete = !isComplete;
    }

    /**
     * Checks if the boolean is complete
     * 
     * @return true if completed else false
     */
    public boolean isDone() {
        return isComplete;
    }

    /**
     * Retrieves the description of the task
     * 
     * @return Description of task
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        String marker = isComplete ? "[X] " : "[ ] ";
        return marker + description;
    }
}
