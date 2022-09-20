package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Events class which is a subclass of Task,
 * encapsulates Events objects
 *
 * @author  Wee Xin Yang, Markus
 * @version 0.1
 * @since   2022-8-24
 */
public class Events extends Task {

    private LocalDate localDate;

    /**
     * Constructor for Events Object
     * @param event the string representation of event to be done
     * @param dateStr the string representation of the date of the event
     */
    public Events(String event, String dateStr) {
        super(event);
        assert event != null : "Please input an Event task!";
        try {
            this.localDate = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            System.out.println(dateStr);

        }
    }

    /**
     * Returns a String object representing this Events' value.
     *
     * @return the string representation of the specified Events
     */
    @Override
    public String toString() {

        return "[E]" + super.toString() + " (by:" + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
