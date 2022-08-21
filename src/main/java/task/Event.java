package task;

public class Event extends Task {
    private String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    private String getDate() {
        return "(at:" + date + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + this.getDate();
    }
}
