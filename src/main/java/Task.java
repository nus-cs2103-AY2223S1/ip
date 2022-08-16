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

    @Override
    public String toString() {
        return description;
    }
}
