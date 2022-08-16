public class Task {
    private final String taskDescription;
    private boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public void isDoneSetter(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + taskDescription;
    }
}
