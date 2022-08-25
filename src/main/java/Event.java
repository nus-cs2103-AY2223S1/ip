public class Event extends Task{
    protected String at;
    protected String string_Date;
    protected String string_Time;

    public Event(String description, String at) {
        super(description);
        this.at = at;

        String[] split_Description = description.split(" ");
        this.string_Date = split_Description[4];
        this.string_Time = split_Description[5];


    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + string_Date + " " + string_Time + ")";
    }
}
