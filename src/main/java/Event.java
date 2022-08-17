public class Event extends Task {

    private String time;

    public Event(String taskname, String time) {
        super(taskname);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time + ")";
    }
}
