public class Task {

    private boolean isDone;
    private String item;
    private static int count = 0;

    public Task(String description) {
        this.item = description;
        this.isDone = false;
        count++;
    }

    public static String getCount() {
        return ("Now you have " + String.valueOf(count) + " tasks in the list.");
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
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
        return("[" + getStatusIcon() + "] " + this.item);
    }
}
