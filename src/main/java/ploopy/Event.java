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
     */
    @Override
    public String getDate() {
        return String.format("(at %s)", super.getDate());
    }
}
