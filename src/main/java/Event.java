public class Event extends Task {
    private String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + date + ")";
    }
}
