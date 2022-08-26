package dukeprogram;

public class Event extends DatedJob {

    public Event(String name, String dueString) {
        super(name, dueString, "at");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
