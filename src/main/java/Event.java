public class Event extends Task {
    protected String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public Event(String description, int status, String date) {
        super(description, status);
        this.date = date;
    }

    @Override
    public String parseToSaveData() {
        return "E" + "|" + super.parseToSaveData() + "|" + date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
