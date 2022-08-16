public class Task {
    private String text;
    private Boolean isComplete;

    public Task(String text) {
        this.text = text;
        this.isComplete = false;
    }

    public void setComplete() {
        this.isComplete = true;
    }

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
