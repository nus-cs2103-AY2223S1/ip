public class Mark extends Task {
    public String command;
    public Mark(String description) {
        super(description);
        this.command = description;
    }

    @Override
    public boolean AddToList() {
        return false;
    }

    @Override
    public String getStatusIcon() {
        this.isDone = true;
        return "X";
    }

    @Override
    public String toString() {
        return "Nice! I've marked this task as done:\n" + "[X] " + this.command.substring(5);
    }
}
