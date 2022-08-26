package anya.task;

public class Event extends Task {
    private String eventAt;

    public Event(String name, String eventAt) {
        super(name);
        this.eventAt = eventAt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventAt + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Task)) {
            return false;
        }

        Event task = (Event) o;
        return this.name.equals(task.name) && this.eventAt.equals(task.eventAt);
    }

    @Override
    public String toSave() {
        String doneVar = super.isDone ? "1" : "0";
        return "E | " + doneVar + " | " + super.name + " | " + this.eventAt;
    }
}
