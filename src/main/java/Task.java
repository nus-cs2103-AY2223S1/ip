public class Task {
    private boolean completed = false;
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void changeState() {
        this.completed = !this.completed;
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }
}
