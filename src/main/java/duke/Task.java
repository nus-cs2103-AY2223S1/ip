package duke;

public class Task {
    private String taskDescription;
    private boolean isCompleted = false;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription.strip();
    }

    public Task(String taskDescription, boolean isCompleted) {
        this.taskDescription = taskDescription;
        this.isCompleted = isCompleted;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }
    protected String returnDescription(){
        String cross = " ";
        if (isCompleted) {
            cross = "X";
        }
        return "[" + cross + "] " + this.taskDescription;
    }

    protected String toWriteFile() {
        String cross = "false";
        if (isCompleted) {
            cross = "true";
        }
        return cross + " , " + this.taskDescription;
    }

    protected void markTask() {
        this.isCompleted = true;
    }

    protected void unmarkTask() {
        this.isCompleted = false;
    }
}
