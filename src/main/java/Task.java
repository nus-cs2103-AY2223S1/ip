public class Task {
    private String taskDescription;
    private boolean isTaskDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isTaskDone = false;
    }

    public Task(String taskDescription, boolean isTaskDone) {
        this.taskDescription = taskDescription;
        this.isTaskDone = isTaskDone;
    }

    public void markDone() {
        this.isTaskDone = true;
    }

    public void unmarkDone() {
        this.isTaskDone = false;
    }

    @Override
    public String toString() {
        return isTaskDone ? "[X] " + this.taskDescription : "[ ] " + this.taskDescription;
    }

    public String toFileString() {
        return isTaskDone ? "1 | " + this.taskDescription : "0 | " + this.taskDescription;
    }

}
