public class Event extends Task {
    protected String at;

    public Event(String description, Boolean isDone, String at) {
        super(description);
        if (isDone) {
            super.markAsDone();
        }
        this.at = at;
    }

    @Override
    public String saveData() {
        String isDone;
        if (super.isDone) {
            isDone = "O";
        } else {
            isDone = "X";
        }
        return String.format("E | %s | %s | %s\n", isDone, super.description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}
