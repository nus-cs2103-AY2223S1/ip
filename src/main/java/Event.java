public class Event extends Task {
    protected String at;
    
    Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String toFileDescription() {
        return "E" + " | " + super.toFileDescription() + " | " + this.at;
    }

    public static Event fromFileDescription(String input) {
        String[] strArray = input.split(" \\| ", 4);
        String description = strArray[2];
        String at = strArray[3];
        Event event = new Event(description, at);
        if (strArray[1].equals("1")) {
           event.markDone();
        }
        return event;
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
