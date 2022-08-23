abstract public class Task {
    private String task;
    private boolean done;

    Task(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + task;
    }

    public String getStatusIcon() {
        return this.done ? "[X]" : "[ ]";
    }

    public void markTaskAsDone() {
        done = true;
    }

    public void unMarkTaskAsDone() {done = false; }

    public String getTask()
    {
        return task;
    }

    public int getDone() {
        return done ? 1 : 0;
    }

    abstract char getType();
    abstract String getDetail();
}
