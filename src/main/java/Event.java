public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public static Event parseFile(String data) {
        String[] details = data.split(" \\| ");
        Event event = new Event(details[2], details[3]);
        if (details[1].equals("1")) {
            event.markAsDone();
        }
        return event;
    }

    @Override
    public String toDataFormat() {
        String completed = "";
        if (this.isDone) {
            completed = "1";
        } else {
            completed = "0";
        }
        return "E | " +  completed + " | " + this.getDescription() + " | " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}