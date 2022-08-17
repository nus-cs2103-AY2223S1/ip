public class Task {
    private final String title;
    private boolean completed;

    public Task(String title) {
        this.title = title;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }
    public boolean isCompleted() {
        return completed;
    }

    public void completeTask() {
        this.completed = true;
    }
    public void unCompleteTask() {
        this.completed = false;
    }
    public String toString() {
        String s = completed ? "[X]" : "[ ]";
        return s + " " + title;
    }
}
