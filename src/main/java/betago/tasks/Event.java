package betago.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import betago.DukeException;

/**
 * Event class is a type of task where users can store their task description
 * and additional date and time information.
 */
public class Event extends Task {

    private String atDate;
    private String atTime;

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
        assert inputs.length == 2 : "Event command input by user should have a date and time.";
        String[] dateFormatPatterns = {"yyyy-MM-dd", "dd-MMM-yyyy", "dd/MM/yyyy"};
        for (int i = 0; i < dateFormatPatterns.length; i++) {
            try {
                LocalDate d = LocalDate.parse(inputs[0], DateTimeFormatter.ofPattern(dateFormatPatterns[i]));
                this.atDate = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                break;
            } catch (DateTimeParseException e) {
                if (i == dateFormatPatterns.length - 1) {
                    throw new DukeException("Please enter the date in one of these formats:\n"
                            + "yyyy-MM-dd, dd-MMM-yyyy, dd/MM/yyyy\n");
                }
            }
        }
        try {
            LocalTime t = LocalTime.parse(inputs[1], DateTimeFormatter.ofPattern("HHmm"));
            this.atTime = t.format(DateTimeFormatter.ofPattern("hhmma"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter the time in the 24-hour time format "
                    + "eg. '2330' for 11.30pm or '0100' for 1am\n");
        }
    }

    /**
     * Returns string representation for event task with date and time.
     *
     * @return String representation for event task.
     */
    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.getTaskDescription()
                + " (at: " + this.atDate + " " + this.atTime + ")";
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
        return "E , " + icon + " , " + this.description + " , " + this.atDate + " , " + this.atTime + "\n";
    }
}
