public class Event extends Task {

    private String time;

    public Event(String command, String time) {
        super(command);
        this.time = time;
    }

    @Override
    public String toString() {
        if (done) {
            return "[E][X] " + this.description + " (at: " + this.time + ")";
        } else {
            return "[E][ ] " + this.description + " (at: " + this.time + ")";
        }
    }
}
