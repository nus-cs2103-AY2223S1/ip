import java.io.IOException;

public class Task {
    protected String description;
    protected boolean isDone;
    protected Type taskType;
    protected String moreInfo;
    protected boolean saveTask;
    protected Storage storage;

    protected enum Type {
        TODO, DEADLINE, EVENT
    }

    //Todo
    public Task(String description, boolean saveTask) {
        this.description = description;
        this.saveTask = saveTask;
    }

    //Event and Deadline
    public Task(String description, String moreInfo, boolean saveTask) {
        this.description = description;
        this.moreInfo = moreInfo;
        this.saveTask = saveTask;
    }

    public String getMoreInfo() {
        return this.moreInfo;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
