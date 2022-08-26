package task;

public class Event extends Task {
    protected String at;
    protected String string_Date;
    protected String string_Time;

    public Event(String description, String at) {
        super(description);
        this.at = at;

        String[] split_Description = at.split(" ");
        this.string_Date = split_Description[0];
        this.string_Time = split_Description[1];


    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + string_Date + " " + string_Time + ")";
    }
}
