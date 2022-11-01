package neo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for Event task.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor for Event class.
     *
     * @param description user input text
     * @param at stores date string
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor for event class.
     *
     * @param description
     */
    public Event(String description) {
        super(description);
    }

    /**
     * Returns event task string in specific format.
     *
     * @return String
     */
    @Override
    public String toString() {
        if (at.equals("")) {
            return "[E]" + super.toString();
        }
        else {
            LocalDate e1 = LocalDate.parse(at);
            return "[E]" + super.toString() + " (on: " + e1.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
    }
}