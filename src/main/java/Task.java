public class Task {
    private String taskDescription;
    private Boolean isTaskDone;
    private static int totalNumberOfTasks = 0;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isTaskDone = false;
        totalNumberOfTasks++;
    }

    public void markDone() {
        this.isTaskDone = true;
    }

    public void unmarkDone() {
        this.isTaskDone = false;
    }

    public static int getTotalNumberOfTasks() {
        return totalNumberOfTasks;
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
