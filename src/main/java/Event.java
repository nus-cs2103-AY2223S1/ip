public class Event extends Task {
    public String time;

    public Event(String details, String time) {
        super(details);
        this.time = time;
    }

    @Override
    public String getTaskIcon() {
        return "E";
    }

    @Override
    public String getDetails() {
        return details + " (at: " + time + ")";
    }
}
