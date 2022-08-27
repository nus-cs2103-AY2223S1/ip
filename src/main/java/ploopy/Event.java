package ploopy;

public class Event extends Task {

    /**
     * {@inheritDoc}
     */
    public Event(String name, String date) {
        super(name, date);
        type = "E";
    }

    /**
     * {@inheritDoc}
     * Adds string representation of date.
     */
    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), getDate());
    }
}
