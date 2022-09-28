package duke.types;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
/**
 * Represents a deadline for a task.
 *
 * @author Aaron Tan
 */
public class Deadline extends Task {
    private LocalDate date;
    private DateTimeFormatter formatter;

    /**
     * Constructor for Deadline class.
     *
     * @param description Description of task.
     * @param isDone If task is done or not done.
     * @param dateString Date represented as a string.
     * @throws DukeException If date is given in an invalid format.
     */
    public Deadline(String description, boolean isDone, String dateString) throws DukeException {
        super(description, isDone);
        try {
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.date = LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("wrong format!");
        }
    }

    /**
     * Generates a String to be saved.
     *
     * @return String in the format of D | isDone | description | date.
     */
    public String saveString() {
        return String.format("D | %s | %s | %s\n", super.isDone ? "1" : "0",
                super.getDescription(), date.format(formatter));
    }

    /**
     * Generates a String for representation during list.
     *
     * @return String in the format of [D][isDone] description (by: date)
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(), date.format(formatter));
    }
}
