package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Creates a new Event from the event command
 */
public class Event extends Task {
    private LocalDateTime at;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh.mma");

    /**
     * Constructor that creates a new event task with the title and time
     *
     * @param msg
     * @param at
     */
    public Event(String msg, LocalDateTime at) {
        super(msg);
        this.at = at;
    }

    /**
     * Gets the event date and time according to the user input and format it correctly
     *
     * @return string that consists of the current date and time input by the user
     */
    @Override
    public String getDateline() {
        return this.at.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));
    }

    /**
     * Prints the correct output for each event task
     *
     * @return string of final output to be printed in the UI
     */
    @Override
    public String toString() {
        return String.format("%s%s (at: %s)", "[E]",
                super.toString(), this.at.format(formatter));
    }

}
