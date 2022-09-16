package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final LocalDate date;
    private final String time;

    /**
     * A constructor to initialize a Deadline object.
     *
     * @param isDone A boolean to indicate if the deadline is completed.
     * @param deadlineDescription A string to describe the deadline.
     * @param index An integer to indicate the position of the duke.Deadline in list of tasks.
     * @param tag A string to describe deadline in one word.
     * @param date A String to indicate the date to be completed by.
     */
    public Deadline(boolean isDone, String deadlineDescription, int index, String tag, LocalDate date, String time) {
        super(isDone, deadlineDescription, index, tag);
        this.date = date;
        this.time = time;
    }

    /**
     * Changes the Deadline from undone to done.
     *
     * @return A String representation on details of the Deadline done.
     */
    public String markDone() {
        return super.markDone();
    }

    /**
     * Changes the Deadline from done to undone.
     *
     * @return A String representation on details of the Deadline undone.
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
     * Returns a string describing the Deadline added to the list of tasks.
     *
     * @return A String representation of Deadline added.
     */
    @Override
    public String printAdded() {
        return "  Yep, it's in!\n    [D][ ] " + this.getDescription() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + time + ")\n  "
                + this.getIndex() + " tasks left, ganbare!\n";
    }

    /**
     * Returns a string on the full details of the Deadline.
     *
     * @return A String representation of task details.
     */
    @Override
    public String printTask() {
        if (!this.getStatus()) {
            return "  " + this.getIndex() + ".[D][ ] " + this.getDescription() + " (by: "
                    + date.format(DateTimeFormatter.ofPattern("d MMM yyyy"))  + " " + time + ") \n #" + this.getTag();
        } else {
            return "  " + this.getIndex() + ".[D][X] " + this.getDescription() + " (by: "
                    + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + time + ") \n #" + this.getTag();
        }
    }

    /**
     * Returns a string representation of the duke.Deadline.
     *
     * @return a String describing the duke.Deadline.
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
     * @return a String representation of deadline to be saved in the hard disk.
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
     * Returns a string describing the Deadline deleted from the list of tasks.
     *
     * @return a String representation of the Deadline deleted.
     */
    @Override
    public String printDeleted() {
        if (!this.getStatus()) {
            return "  Deadline deleted!\n    [D][ ] " + this.getDescription()
                    + "(by: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy"))  + " " + time + ")";
        } else {
            return "  Deadline deleted!\n    [D][X] " + this.getDescription()
                    + "(by: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy"))  + " " + time + ")";
        }
    }

    @Override
    public String tag(String hashtag) {
        return super.tag(hashtag);
    }
}
