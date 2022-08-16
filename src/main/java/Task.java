public class Task {
    private final String name;
    private boolean isComplete;

    public Task(String name) {
        this.name = name;
        this.isComplete = false;
    }

    public String getName() {
        return name;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String toString() {
        String checkbox = this.isComplete ? "[X]" : "[ ]";
        return checkbox + " " + this.name;
    }
}