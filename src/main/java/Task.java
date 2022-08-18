public class Task {
    private String taskDescription;
    private Boolean isTaskDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isTaskDone = false;
    }

    public void markDone() {
        this.isTaskDone = true;
    }

    public void unmarkDone() {
        this.isTaskDone = false;
    }

    @Override
    public String toString() {
        if (isTaskDone) {
            return "[X] " + this.taskDescription;
        } else {
            return "[ ] " + this.taskDescription;
        }
    }


}
