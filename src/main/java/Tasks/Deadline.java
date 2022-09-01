package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Creates a new deadline task from the deadline command
 */
public class Deadline extends Task {
    private LocalDateTime by;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh.mma");

    /**
     * Constructor that creates the deadline task with the title and time
     *
     * @param msg
     * @param by
     */
    public Deadline(String msg, LocalDateTime by) {
        super(msg);
        this.by = by;
    }

    /**
     * Gets the event date and time according to the user input and format it correctly
     *
     * @return string that consists of the current date and time input by the user
     */
    @Override
    public String getDateline() {
        return this.by.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));
    }

    /**
     * Prints the correct output for each deadline task
     *
     * @return string of final output to be printed in the UI
     */
    @Override
    public String toString() {
        return String.format("%s%s (by: %s)", "[D]", super.toString(),
                this.by.format(formatter));
    }
}
