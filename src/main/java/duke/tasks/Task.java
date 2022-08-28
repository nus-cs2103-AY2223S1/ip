package duke.tasks;

public class Task {
    private String description;
    private Boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }
    
    public String saveString() {
        return (isDone ? 1 : 0) + " | " + description;
    }
    
    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "]" + " " + this.description;
    }
}
