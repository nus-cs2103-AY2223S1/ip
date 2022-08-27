package duke;

import java.time.LocalDate;

import static duke.DukeConstants.KEY_SEPARATOR;

public class Deadline extends Task {

    private LocalDate date;

    /**
     * Takes in a description and deadline for the task
     * @param description task description
     * @param date deadline of task
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String formatToSave() {
        return isDone
                ? "D" + KEY_SEPARATOR + 1 + KEY_SEPARATOR + description + KEY_SEPARATOR + date
                : "D" + KEY_SEPARATOR + 0 + KEY_SEPARATOR + description + KEY_SEPARATOR + date;
    }
    /**
     * Returns a String representation of the task
     * @return string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
