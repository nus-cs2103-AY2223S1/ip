package task;

/**
 * The Events class which is a subclass of Task,
 * encapsulates Events objects
 *
 * @author  Wee Xin Yang, Markus
 * @version 0.1
 * @since   2022-8-24
 */
public class Events extends Task {

    private final String date;

    public Events(String event, String date) {
        super(event);
        assert event != null : "Please input an Event task!";
        this.date = date;
    }

    /**
     * Returns a String object representing this Events' value.
     *
     * @return the string representation of the specified Events
     */
    @Override
    public String toString() {

        return "[E]" + super.toString() + " (at:" + date + ")";
    }
}
