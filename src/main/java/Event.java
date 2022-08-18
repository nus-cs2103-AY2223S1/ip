public class Event extends Task {

    protected String at;

    public Event(String task, String at) {
        super(task);
        this.at = at;
    }


    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
