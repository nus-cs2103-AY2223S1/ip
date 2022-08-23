public class Event extends Task{
    String eventAt;

    public Event(String name, String eventAt) {
        super(name);
        this.eventAt = eventAt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventAt + ")";
    }

    @Override
    public String toSave() {
        String doneVar = super.isDone ? "1" : "0";
        return "E | " + doneVar + " | " + super.name + " | " + this.eventAt;
    }
}
