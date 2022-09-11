package ploopy.task;

public class Event extends Task {

    /**
     * {@inheritDoc}
     */
    public Event(String name, String date) {
        super(name, date);
        type = "E";
    }

    @Override
    public String toString() {
        return String.format("%s %s%s", super.toString(), getDate(), getPriorityForString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDate() {
        return String.format("(at: %s)", super.getDate());
    }
}
