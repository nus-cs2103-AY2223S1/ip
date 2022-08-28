package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a subclass of Task. It allows the user the add an additional input
 * parameter which specifies the deadline of the task.
 */
public class Deadline extends Task {
    /** The deadline of the task. */
    protected LocalDateTime by;

    /**
     * The class constructor, which utilizes the superclass
     * constructor.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        if (by.trim().matches("(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})")) {
            String preDate = by.split(" ", 2)[0];
            String preTime = by.split(" ", 2)[1];
            String[] dates = preDate.split("-", 3);
            String[] time = preTime.split(":", 2);

            LocalDateTime deadline = LocalDateTime.of(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]),
                    Integer.parseInt(dates[2]), Integer.parseInt(time[0]), Integer.parseInt(time[1]));

            this.by = deadline;
        } else {
            throw new DukeException("Invalid Date Format (YYYY-MM-DD HH:MM required).");
        }
    }

    /**
     * Returns a string of  deadline to be displayed, formatted
     * as MONTH DATE YEAR i.e. MARCH 19 2022.
     *
     * @return String of the deadline to be displayed.
     */
    public String formatTime() {
        if (this.by.getMinute() < 10) {
            String minute = String.format("%02d", this.by.getMinute());
            String s = String.format("%s %s %s %s:%s", this.by.getMonth(), this.by.getDayOfMonth(),
                    this.by.getYear(), this.by.getHour(), minute);
            return s;
        }
        String s = String.format("%s %s %s %s:%s", this.by.getMonth(), this.by.getDayOfMonth(),
                this.by.getYear(), this.by.getHour(), this.by.getMinute());
        return s;
    }

    /**
     * Returns a string representation of a Deadline object formatted
     * for writing into text file.
     *
     * @return String of the deadline formatted to saved.
     */
    @Override
    public String formatFileText() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String s = String.format("D | %s | %s | %s\n", super.getStatusIcon(), this.description, this.by.format(format));
        return s;
    }

    /**
     * Overrides the toString method of the superclass to add
     * the additional [D] tag.
     *
     * @return String representation of this task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatTime() + ")";
    }

}
