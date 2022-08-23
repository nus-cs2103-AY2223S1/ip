package duke.task;

public class Event extends Task {

    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public static Event parseFile(String string) {
        String[] details = string.split(" \\| ");
        Event event = new Event(details[2], details[3]);
        if (details[1].equals("1")) {
            event.markAsDone();
        }
        return event;
    }

    @Override
    public String toDataFormat() {
        String completed = "0";
        if (this.getStatusIcon().equals("X")) {
            completed = "1";
        }
        return "E | " + completed + " | " + this.getDescription() + " | " + this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
