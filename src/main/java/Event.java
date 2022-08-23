public class Event extends Task {
    protected String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getTask() {
        String done = this.isDone ? "1" : "0";
        return String.format("E | %s | %s | %s", done, this.description, this.date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }
}
