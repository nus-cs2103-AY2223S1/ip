public class Add extends Task {
    public String command;
    public Add(String description) {
        super(description);
        this.command = description;
    }

    @Override
    public String toString() {
        return "[ ][ ] " + this.command;
    }
}
