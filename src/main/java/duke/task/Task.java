package duke.task;

/**
 * The superclass of all tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description =  description;
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    protected String getSavedStatusIcon() {
        return (isDone ? "1" : "0");
    }

    public void markAsDone(){
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String taskInfo() {
        return "[] [" + getStatusIcon() + "] " + description;
    }

    public String taskSaveInfo() {
        return "," + getSavedStatusIcon() + "," + description;
    }


}
