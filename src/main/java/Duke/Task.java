package Duke;

abstract public class Task {
    private String task;
    private boolean isDone;

    Task(String task, boolean done) {
        this.task = task;
        this.isDone = done;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + task;
    }


    public String getStatusIcon() {
        return this.isDone ? "[X]" : "[ ]";
    }

    public String getTask() {
        return this.task;
    }

    public void setTaskAsDone() {
        isDone = true;
    }


    public void setTaskAsUnDone() {
        isDone = false;
    }

    public int getDone() {
        return isDone ? 1 : 0;
    }

    abstract char getType();

    abstract String getOriginalDetail();

    abstract String getFormattedDetail();
}
