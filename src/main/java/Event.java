public class Event extends Task{
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String getStatus() {
        if(this.isDone) {
            return "[E][X]";
        } else {
            return "[E][ ]";
        }
    }
    @Override
    public String toString() {
        return this.getStatus() + " " + this.description + " (at: " + this.time + ")";
    }
}
