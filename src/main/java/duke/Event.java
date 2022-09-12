package duke;

/**
 * Event class is a Task that contains a date and time
 */
public class Event extends Task {

    protected String at;
    private final static String EVENT_START_STR = "[E][";
    private final static int IS_DONE_INDEX = 4;


    /**
     * Constructor for Event.
     *
     * @param description Description of the task
     * @param at          Date and time
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Second constructor for Event that takes in a boolean to indicate if the event is over
     *
     * @param description Description of the task
     * @param at          Date and time
     * @param isDone      Boolean to indicate if the event is over
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }

    /**
     * Returns an event that is constructed from a string with a particular format
     * "[E][ ] Description (at: Date and Time)"
     *
     * @param s String to be converted to an Event
     * @return Event that is constructed from a string with a particular format
     * @throws DukeException When string does not begin with the event header "[E]"
     */
    // duke.Event strings look like: [E][ ] project meeting (at: Aug 6th 2-4pm)
    public static Event stringToEvent(String s) throws DukeException {
        if (!s.startsWith(EVENT_START_STR)) {
            throw new DukeException("This string is not a duke.Event string!");
        }

        char isDoneString = s.charAt(IS_DONE_INDEX); //[E][X] checks if X is present
        char X = 'X';
        boolean isDone = isDoneString == X;

        int idxOfAt = s.indexOf("(at:");
        assert(idxOfAt > 0);

        String description = s.substring(7, idxOfAt);
        String dateString = s.substring(idxOfAt + 5, s.length() - 1); // to avoid the brackets
        return new Event(description, dateString, isDone);
    }

    public static void main(String[] args) throws DukeException {
        String testString = "[E][ ] project meeting (at: Aug 6th 2-4pm)";
        System.out.println(stringToEvent(testString).toString().equals(testString));
    }
}
