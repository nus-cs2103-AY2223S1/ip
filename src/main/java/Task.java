public class Task {
    private String taskDescription;
    private boolean isCompleted = false;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    protected String returnDescription(){
        String cross = " ";
        if (isCompleted) {
            cross = "X";
        }
        return "[" + cross + "] " + this.taskDescription;
    }

    protected void markTask() {
        this.isCompleted = true;
    }

    protected void unmarkTask() {
        this.isCompleted = false;
    }
}
