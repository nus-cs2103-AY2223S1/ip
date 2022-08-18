public class Event extends Task {

    private String dateTime;

    public Event(String name, String dateTime) {
        super(name.substring(6));
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + this.dateTime + ")";
    }
}
