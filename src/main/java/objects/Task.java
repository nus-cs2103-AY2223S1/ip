package objects;

public class Task {
    private String name;
    private Boolean isDone = false;

    public Task(String name) {
        this.name = name.trim();
    }

    public Task(String name, Boolean isDone) {
        this.name = name.trim();
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public Boolean getIsDone() {
        return this.isDone;
    }

    public String getStatus() {
        return this.isDone ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return getStatus() + " " + getName();
    }
}
