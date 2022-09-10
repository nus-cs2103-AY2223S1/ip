package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final LocalDate date;
    private final String time;

    /**
     * A constructor to initialize a duke.Deadline object.
     *
     * @param isDone A boolean to indicate if the deadline is completed.
     * @param deadlineDescription A string to describe the deadline.
     * @param index An integer to indicate the position of the duke.Deadline in list of tasks.
     * @param date A String to indicate the date to be completed by.
     */
    public Deadline(boolean isDone, String deadlineDescription, int index, LocalDate date, String time) {
        super(isDone, deadlineDescription, index);
        this.date = date;
        this.time = time;
    }

    /**
     * Updates the deadline from incomplete to complete.
     */
    public String markDone() {
        return super.markDone();
    }

    /**
     * Updates the deadline from complete to incomplete.
     */
    public String markUndone() {
        return super.markUndone();
    }

    /**
     * Updates the index of the deadline in the list of tasks.
     *
     * @param newIndex The most current integer index of the task.
     */
    @Override
    public void setIndex(int newIndex) {
        super.setIndex(newIndex);
    }

    /**
     * Outputs the deadline added to the list of tasks in the console.
     */
    @Override
    public String printAdded() {
        return "\n  Yep, it's in!\n    [D][ ] " + this.getDescription() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + time + ")\n  "
                + this.getIndex() + " tasks left, ganbare!\n";
    }

    /**
     * Outputs the full details of the deadline in the console.
     */
    @Override
    public String printTask() {
        if (!this.getStatus()) {
            return "  " + this.getIndex() + ".[D][ ] " + this.getDescription() + " (by: "
                    + date.format(DateTimeFormatter.ofPattern("d MMM yyyy"))  + " " + time + ")";
        } else {
            return "  " + this.getIndex() + ".[D][X] " + this.getDescription() + " (by: "
                    + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + time + ")";
        }
    }

    /**
     * Returns a string representation of the duke.Deadline.
     *
     * @return string describing the duke.Deadline.
     */
    @Override
    public String toString() {
        if (!this.getStatus()) {
            return this.getIndex() + ".[D][ ] " + this.getDescription() + " | by: " +
                    date.format(DateTimeFormatter.ofPattern("d MMM yyyy"))  + " | " + time;
        } else {
            return this.getIndex() + ".[D][X] " + this.getDescription() + " | by: " +
                    date.format(DateTimeFormatter.ofPattern("d MMM yyyy"))  + " | " + time;
        }
    }

    /**
     * Returns a string to be saved in the hard disk.
     *
     * @return A string representation of deadline to be saved in the hard disk.
     */
    @Override
    public String toSavedString() {
        if (!this.getStatus()) {
            return "DN<" + this.getDescription() + ">" + "(" +
                    date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")" + "{" + time + "}" + "/n";
        } else {
            return "DY<" + this.getDescription() + ">" + "(" +
                    date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")" + "{" + time + "}" + "/n";
        }
    }

    /**
     * Outputs the full details of the deadline being deleted in the console.
     */
    public String printDeleted() {
        if (!this.getStatus()) {
            return "\n  duke.Task deleted!\n    [D][ ] " + this.getDescription()
                    + "(by: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy"))  + " " + time + ")";
        } else {
            return "\n  duke.Task deleted!\n    [D][X] " + this.getDescription()
                    + "(by: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy"))  + " " + time + ")";
        }
    }
}
