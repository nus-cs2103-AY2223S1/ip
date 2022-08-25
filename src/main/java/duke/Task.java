package duke;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    public void setDone() {
        if (this.isDone) {
            return;
        }
        this.isDone = true;
    }

    public void setNotDone() {
        if (!this.isDone) {
            return;
        }
        this.isDone = false;
    }

    public boolean startsWith(String prefix) {
        return description.startsWith(prefix);
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }

    public String toStorageFormat() {
        if (this.isDone) {
            return "1 | " + description;
        } else {
            return "0 | " + description;
        }
    }
}
