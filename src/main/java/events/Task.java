package events;
public class Task {
    private final String text;
    private Boolean isComplete;

    public Task(String text) {
        this.text = text;
        this.isComplete = false;
    }

    /**
     * Sets the task to be complete.
     */
    public void setComplete() {
        this.isComplete = true;
    }

    /**
     * Sets the task to be incomplete.
     */
    public void setIncomplete() {
        this.isComplete = false;
    }

    @Override
    public String toString() {
        return this.isComplete
                ? String.format("[X] %s", this.text)
                : String.format("[ ] %s", this.text);
    }
}
