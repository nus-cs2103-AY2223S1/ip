public class Task {

    private boolean isDone;
    private String item;

    public Task(String description) {
        this.item = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String markAsDone() {
        this.isDone= true;
        return("Nice! I've marked this task as done:\n  [X] " + this.item);
    }

    public String markAsNotDone() {
        this.isDone= false;
        return("OK, I've marked this task as not done yet:\n  [ ] " + this.item);
    }

    @Override
    public String toString() {
        return("[" + getStatusIcon() + "] " + this.item);
    }
}
