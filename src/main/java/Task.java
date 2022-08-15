public class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (this.isDone ? "☒" : "☐");
    }

    public void setCompletion(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.desc;
    }

}
