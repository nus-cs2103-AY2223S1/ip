public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {

        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void done(boolean done) {

        this.isDone = done;
    }


    public String toFileString() {

        return " | " + (this.isDone ? 1 : 0) + " | " +
                this.description;
    }

    @Override
    public String toString() {

        return "[" + this.getStatusIcon() + "] " + this.description;
    }


}
