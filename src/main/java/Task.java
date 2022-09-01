public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTask() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getBinaryStatus() {
        return (isDone ? "1" : "0");
    }

    public void setStatus(int status) {
        if (status == 0) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
    }

    public String getDue() {
        return "";
    }

    public String getTaskType() {
        return "";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}