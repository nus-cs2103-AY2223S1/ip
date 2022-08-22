public class Event extends Task {

    protected final String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    public Event(String name, String time, boolean isDone) {
        super(name, isDone);
        this.time = time;
    }

    public String toString() {
        return String.format("[%s]%s (at: %s)", this.getType(), super.toString(), this.getTime());
    }

    public String toFileString() {
        return String.format("%s||%s", super.toFileString(), this.getTime());
    }

    public String getTime() {
        return this.time;
    }
    public String getType() {
        return "E";
    }
}