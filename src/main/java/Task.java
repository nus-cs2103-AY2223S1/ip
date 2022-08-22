public class Task {

    private String task;
    private int id;
    private static int counter = 0;
    private boolean isDone;

    public Task(String task) {
        this.task = task;
        counter ++;
        id = counter;
        isDone = false;
    }

    protected String getTask() {
        return task;
    }

    protected int getId() {
        return id;
    }

    protected String getStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    protected void mark() {
        isDone = true;
    }

    protected void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return getStatus() + " " + task;
    }

}
