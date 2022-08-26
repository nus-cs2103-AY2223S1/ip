public class Event extends Task {
    private String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String save() {
        return "E" + super.save() + " | " + this.date;
    }
}
