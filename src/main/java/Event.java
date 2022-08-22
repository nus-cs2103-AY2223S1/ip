public class Event extends Task {
    private String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[" + TaskType.EV +"]" + "[" + this.getStatusIcon() + "] " + this.getName() +  " (at:" + this.getTime() + ")";
    }
}
