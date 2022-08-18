public class Unmark extends Task {
    public String command;
    public Unmark(String description) {
        super(description);
        this.command = description;
    }

    @Override
    public boolean AddToList() {
        return false;
    }

    @Override
    public String getStatusIcon() {
        this.isDone = false;
        return " ";
    }

    @Override
    public String toString() {
        return "OK, I've marked this task as not done yet:\n" + "[ ] " + this.command.substring(5);
    }
}
