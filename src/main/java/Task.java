public class Task {

    protected int id;
    protected String description;
    protected boolean isDone;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public static void setDone(Task task, boolean isDone) {
        task.setDone(isDone);
    }

    public String toStringWithId() {
        return String.format("%d.%s", id, toString());
    }

    @Override
    public String toString() {
        return String.format("%s %s", getStatusIcon(), description);
    }
}
