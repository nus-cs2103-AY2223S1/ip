public class Event extends Task{
    String time;

    Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), time);
    }
}
