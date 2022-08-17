public abstract class Task {

    private final String taskname;
    private boolean isDone;

    public Task(String taskname) {
        this.taskname = taskname;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return "[" + status + "]" + " " + this.taskname;
    }



}
