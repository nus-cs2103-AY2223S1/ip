package duke.task;

public class Task {
    // consider making this an abstract class
    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void mark() {
        if (this.isDone) {
            System.out.println("  You can't finish the same task twice, genius.");
        } else {
            System.out.println("  You really took your time with this one, didn't you?");
            this.isDone = true;
        }
        System.out.println("    " + this);
    }

    public void unmark() {
        if (!this.isDone) {
            System.out.println("  You're trying to unmark a task you haven't done.\n"
                    + "  Let that sink in for a moment.");
        } else {
            System.out.println("  And here I was thinking you were getting somewhere...");
            this.isDone = false;
        }
        System.out.println("    " + this);
    }

    public String getSaveFormat() {
        return "| " + (isDone ? 1 : 0) + " | " + this.desc;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.desc;
    }
}
