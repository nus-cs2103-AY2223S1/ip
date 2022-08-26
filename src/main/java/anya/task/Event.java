package anya.task;

public class Event extends Task {
    private String eventAt;

    public Event(String name, String eventAt) {
        super(name);
        this.eventAt = eventAt;
    }

    /**
     * {@inheritDoc}
     * The type of task, E, is appended to the front of the String.
     *
     * @return D, the status icon, the name of task, and the location of the event in a String.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventAt + ")";
    }

    /**
     * Compares two event and returns true if the event names and locations are identical.
     *
     * @param o the object to be compared against.
     * @return true if the event names and locations are identical; false otherwise.
     */
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

    /**
     * {@inheritDoc}
     * E represents an Event.
     * 0 represents an incomplete task while 1 represents a completed task.
     * The location of the event is a String.
     *
     * @return E, an integer representing whether the task is completed, the task name,
     * and the location of the event.
     */
    @Override
    public String toSave() {
        String doneVar = super.isDone ? "1" : "0";
        return "E | " + doneVar + " | " + super.name + " | " + this.eventAt;
    }
}
