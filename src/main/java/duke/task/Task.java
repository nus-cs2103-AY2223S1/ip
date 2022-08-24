package duke.task;

public abstract class Task {
    private boolean hasCompleted = false;
    private String name;

    public Task(String name) {

        this.name = name;
    }

    public void setCompleted() {

        this.hasCompleted = true;
    }

    public boolean getHasCompleted() {

        return this.hasCompleted;
    }

    public void setUncompleted() {

        this.hasCompleted = false;
    }

    public String toString() {
        if (hasCompleted) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }

    public abstract String parse();
}
