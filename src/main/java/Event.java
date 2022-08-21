public class Event extends Task {
    String time;

    public Event (TaskType type, String name, boolean marked, String time) {
        super(type, name, marked);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(at:" + time + ")";
    }
}
