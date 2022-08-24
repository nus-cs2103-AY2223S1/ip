public class Event extends Task {
    private String at;

    Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public static Event taskFromSave(String saveString) {
        String[] tokens = saveString.split(" \\| ");
        Event event = new Event(tokens[2], tokens[3]);
        if (tokens[1].equals("1")) {
            event.markDone();
        }
        return event;
    }

    @Override
    public String saveString() {
        return "E | " + super.saveString() + " | " + at;
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
