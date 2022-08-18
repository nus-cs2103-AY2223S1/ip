public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public String getTask() {
        String mark = " ";
        if (this.isDone) {
            mark = "X";
        }
        return "[" + type + "][" + mark + "]" + this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }
}
