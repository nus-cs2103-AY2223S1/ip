public class Unmark extends Task {
    public String command;
    public Unmark(String description) {
        super(description);
        this.command = description;
    }

    @Override
    public String toString() {
        return "OK, I've marked this task as not done yet:\n" + this.command.substring(0, 3) + "[ ]" + this.command.substring(6);
    }
}
