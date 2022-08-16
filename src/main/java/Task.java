public class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void setDone(boolean status) {
        if (this.isDone == true && status == true) {
            System.out.println("  You can't finish the same task twice, genius.");
        } else if (this.isDone == false && status == false) {
            System.out.println("  You're trying to unmark a task you haven't done.\n"
                    + "  Let that sink in for a moment.");
        } else if (status) {
            System.out.println("  You really took your time with this one, didn't you?");
        } else {
            System.out.println("  And here I was thinking you were getting somewhere...");
        }
        this.isDone = status;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.desc;
    }
}
