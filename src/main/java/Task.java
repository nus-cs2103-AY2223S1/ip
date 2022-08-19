public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String getDescription() {
        return description;
    }

    public String markAsDone() {
        this.isDone = true;
        return "Nice! I've marked this task as done: \n" + this;

    }

    public String unmarkAsNotDone() {
        this.isDone = false;
        return "Ok! I've marked this task as not done yet: \n" + this;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + getDescription();
    }

}