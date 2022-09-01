package duke.task;

public class Event extends Task {

    private static final String EVENT_LETTER = "E";

    // Event is of the form "event description /at dateAndTime"
    protected final String dateAndTime;


    // Constructors
    public Event(String description, String dateAndTime) {
        this(description, false, dateAndTime);
    }

    public Event(String description, boolean isDone, String dateAndTime) {
        super(description, isDone);
        this.dateAndTime = dateAndTime;
    }
    

    @Override
    public Event markTask() {
        return new Event(description, true, dateAndTime);
    }

    @Override
    public Event unmarkTask() {
        return new Event(description, false, dateAndTime);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", EVENT_LETTER, super.toString(), dateAndTime);
    }
}
