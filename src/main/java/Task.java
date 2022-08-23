public class Task {

    private boolean isDone;
    private String descript;
    private static int count = 0;

    public Task(String description) {
        this.descript = description;
        this.isDone = false;
        count++;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public String getDate() {
        return "";
    }

    public String getDescription() {
        return this.descript;
    }

    public String markAsDone() {
        this.isDone= true;
        return("Nice! I've marked this task as done:\n  " + this);
    }

    public String markAsNotDone() {
        this.isDone= false;
        return("OK, I've marked this task as not done yet:\n  " + this);
    }

    @Override
    public String toString() {
        return("[" + getStatusIcon() + "] " + this.descript);
    }
}
