public class Events extends Task {
    protected String by;

    public Events(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String textFormat() {
        return "E|" + (isDone ? 1 : 0) + "|" + description + "|" + by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + by + ")";
    }

}
