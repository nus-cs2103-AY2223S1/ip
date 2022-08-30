package ip.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import ip.exception.BadDeadline;
import ip.exception.MissingDescription;

/**
 * Encapsulation of a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;
    /** Format to use for parsing date time input. */
    private DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    /** Format to use for outputting date time input. */
    private DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("d MMM, HH:mm");

    /**
     * Constructor to create a deadline object.
     *
     * @param options Contains the description and deadline of the task.
     * @throws MissingDescription If there is no description in options.
     * @throws BadDeadline If the deadline is missing or in the wrong format.
     */
    public Deadline(Scanner options) throws MissingDescription, BadDeadline {
        if (options.hasNext()) {
            options.useDelimiter(" /by ");
            String description = options.next().substring(1);
            String dateInput = "";
            if (options.hasNext()) {
                try {
                    dateInput = options.next();
                    super.describe(description);
                    super.unmark();
                    this.deadline = LocalDateTime.parse(dateInput, inputFormat);
                    System.out.println("CREATED DEADLINE: " + description + " DUE ON: "
                            + deadline.format(outputFormat));
                } catch (DateTimeParseException e) {
                    System.out.println("INVALID DATE-TIME INPUT FORMAT DETECTED. ABORTING COMMAND.");
                    throw new BadDeadline(dateInput);
                }
            } else {
                throw new BadDeadline(dateInput);
            }
        } else {
            throw new MissingDescription();
        }
    }

    /**
     * Constructor to create deadline object from formatted string.
     *
     * @param props Contains data used to build the deadline object.
     */
    public Deadline(String[] props) {
        super.describe(props[2]);
        this.deadline = LocalDateTime.parse(props[3], inputFormat);
        if (props[1].equals("true")) {
            super.mark();
        }
    }

    public String writeFormat() {
        return "d|" + isComplete + "|" + description + "|" + deadline.format(inputFormat) + "|\n";
    }

    @Override
    public boolean hasString(String s) {
        return super.description.contains(s);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (Deadline: " + deadline.format(outputFormat) + ")";
    }
}
