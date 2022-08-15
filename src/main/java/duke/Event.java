package duke;

public class Event extends Task{
    private final String tag = "[E]";
    private final String event;

    public Event(String name, String event) {
        super(name);
        this.event = event;
    }


    @Override
    public String toString() {
        return tag + "[" + this.getStatusIcon()  + "] " + this.getTaskName() + "(" + event + ")";
    }
}
