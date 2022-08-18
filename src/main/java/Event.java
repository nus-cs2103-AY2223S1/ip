public class Event extends Task {

    private String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E] " + (super.isCompleted() ? "[X] " : "[ ] ") + super.getTaskName() + "(at:" + this.time + ")";
    }

    public String getTime() {
        return this.time;
    }

}
