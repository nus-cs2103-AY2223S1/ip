package task;

public class Event extends Task {
    protected String at;
    protected String string_Date;
    protected String string_Time;

    public Event(String description, String at) {
        super(description);
        this.at = at;

        String splitDescription[] = at.split(" ");
        this.string_Date = splitDescription[0];
        this.string_Time = splitDescription[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + string_Date + " " + string_Time + ")";
    }
}
