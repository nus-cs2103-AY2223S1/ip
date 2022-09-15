package task;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Deadlines class, which is a subclass of Task,
 * encapsulates Deadlines objects
 *
 * @author  Wee Xin Yang, Markus
 * @version 0.1
 * @since   2022-8-24
 */
public class Deadlines extends Task {

    private LocalDate localDate;

    /**
     * Constructor for Deadlines Object
     * @param deadline the string representation of deadline to be done
     * @param dateStr the string representation of the date of the event
     */
    public Deadlines(String deadline, String dateStr) throws ParseException {
        super(deadline);
        assert deadline != null : "Please input a Deadline task!";
        try {
            this.localDate = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            System.out.println("Please input dates in the following format: yyyy-mm-dd (eg. 2019-10-15)");

        }
    }

    /**
     * Returns a String object representing this Deadlines' value.
     *
     * @return the string representation of the specified Deadlines
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
