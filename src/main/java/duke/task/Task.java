package duke.task;

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

    public String TaskInfo() {
        return "[] [" + getStatusIcon() + "] " + description;
    }

    public String TaskSaveInfo() {
        return "," + getSavedStatusIcon() + "," + description;
    }

    public boolean containKeyword(String keyword) {
        return this.description.contains(keyword);
    }

}
