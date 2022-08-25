package Duke;

public class Task {
    private String description;
    private boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone? "X": " ");
    }

    @Override
    public String toString() {
        return "["+ getStatusIcon()+"] "+this.description;
    }
}
