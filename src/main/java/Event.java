public class Event extends Task{
    String time;

    Event(String name, String time) {
        super(name);
        this.time = time;
    }

    Event(String name, String time, boolean status) {
        super(name, status);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), time);
    }

    //Returns time of task
    public String getTime() {
        return time;
    }
}
