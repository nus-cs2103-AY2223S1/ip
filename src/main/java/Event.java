public class Event extends Task {
    String time;

    public Event (TaskType type, String name, String time) {
        super(type, name);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(at:" + time + ")";
    }
}
