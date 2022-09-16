package duke;

/**
 * An Event is a type of Task with a date at which it occurs.
 */
public class Event extends Task {

    private String at;

    /**
     * Constructs an Event.
     *
     * @param name Name of the event.
     * @param isDone Whether the event has been completed.
     * @param at Time when the event is held.
     * @throws DukeTaskException  If time is empty.
     */
    public Event(String name, boolean isDone, String at) throws DukeTaskException {
        super(name, isDone);
        if (at.equals("")) {
            throw new DukeTaskException("time can't be empty");
        }
        this.at = at;
    }

    /**
     * Loads an Event from a string from the save file.
     *
     * @param s String representing contents of an Event.
     * @return Event with specified parameters.
     * @throws DukeException  If time specifier in string is empty.
     */
    public static Event load(String s) throws DukeException {
        String at = s.substring(1, s.indexOf("|")).trim();
        String name = s.substring(s.indexOf("|") + 1, s.lastIndexOf("|")).trim();
        String isDone = s.substring(s.lastIndexOf("|") + 1).trim();
        return new Event(name, Boolean.parseBoolean(isDone), at);
    }

    /**
     * Returns a string to save to the save file.
     *
     * @return String representing the Event contents.
     */
    @Override
    public String saveString() {
        return "E " + at + "|" + super.saveString();
    }

    /**
     * Returns the string representation of the event.
     *
     * @return Event as a string.
     */
    @Override
    public String toString() {
        String temp;
        if (super.isDone) {
            temp = "[X] " + super.name;
        } else {
            temp = "[ ] " + name;
        }
        return "[E]" + temp + " (at: " + at + ")";
    }


}
