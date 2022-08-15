public class Event extends Task{
    private String time;
    public Event(String task, String time) {
        super(task);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + this.time + ")";
    }
}
