package betago.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import betago.DukeException;

/**
 * Event class is a type of task where users can store their task description
 * and additional date and time information.
 */
public class Event extends Task {

    private String at;

    /**
     * Constructor for Event task.
     *
     * @param description Description of the task.
     * @param at Date and time of the event.
     * @throws DukeException If date is given in the wrong format.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        String[] inputs = at.split(" ", 2);
        String[] formatPatterns = {"yyyy-MM-dd", "dd-MMM-yyyy", "dd/MM/yyyy"};
        for (int i = 0; i < formatPatterns.length; i++) {
            try {
                LocalDate d = LocalDate.parse(inputs[0], DateTimeFormatter.ofPattern(formatPatterns[i]));
                this.at = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                break;
            } catch (DateTimeParseException e) {
                if (i == formatPatterns.length - 1) {
                    throw new DukeException("Please enter the date and time in these format:\n"
                            + "yyyy-MM-dd, dd-MMM-yyyy, dd/MM/yyyy\n");
                }
            }
        }
        if (inputs.length == 2) {
            this.at = this.at + ", " + inputs[1];
        }
    }

    /**
     * Returns string representation for event task with date and time.
     *
     * @return String representation for event task.
     */
    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.getTaskDescription() + " (at: " + at + ")";
    }

    /**
     * Returns corresponding string representation of the event task that is saved in the data file.
     *
     * @return String representation for event task to be saved in data file.
     */
    @Override
    public String saveTask() {
        String icon;
        if (this.getStatusIcon() == "X") {
            icon = "1";
        } else {
            icon = "0";
        }
        return "E , " + icon + " , " + this.description + " , " + this.at + "\n";
    }
}
