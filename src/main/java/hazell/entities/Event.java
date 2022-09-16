package hazell.entities;

import hazell.exceptions.TaskDescriptionEmpty;

/**
 * A task that should be done at a specific time.
 */
public class Event extends TimeSensitiveTask {
    private static final String typeIcon = "E";
    private String time;

    protected Event(boolean isDone, String description, String time) {
        super(isDone, description);
        this.time = time;
    }

    /**
     * Creates a new Event task via factory method.
     *
     * @param description Description of the Event
     * @param time The time by which the event should be done at
     * @return An Event object
     * @throws TaskDescriptionEmpty If the description is empty
     */
    public static Event create(String description, String time) throws TaskDescriptionEmpty {
        Event event = new Event(false, description, time);
        event.validate();
        return event;
    }

    @Override
    public void postpone(String time) {
        this.time = time;
    }

    @Override
    public String serialise() {
        return String.format("%s | %s | %s | %s",
                typeIcon,
                this.getIsDone() ? 1 : 0,
                this.getDescription(),
                this.time);
    }


    /**
     * Recreates an Event object from a string.
     *
     * @param words An array of words in which the original Event was serialised into
     * @return The unserialised Event object
     */
    public static Event unserialise(String[] words) {
        return new Event(
                words[1].equals("1"),
                words[2],
                words[3]);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", typeIcon, super.toString(), this.time);
    }
}
