public class Event extends Task {
    private String periodDateTime;

    public Event(String name, String periodDateTime) {
        super(name);
        this.periodDateTime = periodDateTime;
    }

    public String getDateTime() {
        return this.periodDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + getStatus() + " " + getName() + "(at: " + getDateTime() + ")";
    }
}
