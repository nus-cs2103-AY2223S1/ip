package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate date;
    private String time;

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
     * A method to update the deadline from incomplete to complete.
     */
    public void markDone() {
        super.markDone();
    }

    /**
     * A method to update the deadline from complete to incomplete.
     */
    public void markUndone() {
        super.markUndone();
    }

    /**
     * A method to update the index of the deadline in the list of tasks.
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
    public void printAdded() {
        System.out.println(Ui.straightLine + "\n  Yep, it's in!\n    [D][ ] " + this.getDescription() + " (by: " +
                            date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + time + ")\n  " +
                            this.getIndex() + " tasks left, 頑張れ!\n" + Ui.straightLine + "\n");
    }

    /**
     * Outputs the full details of the deadline in the console.
     */
    @Override
    public void printTask() {
        if (!this.getStatus()) {
            System.out.println("  " + this.getIndex() + ".[D][ ] " + this.getDescription() + " (by: " +
                                date.format(DateTimeFormatter.ofPattern("d MMM yyyy"))  + " " + time + ")");
        } else {
            System.out.println("  " + this.getIndex() + ".[D][X] " + this.getDescription() + " (by: " +
                    date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + time + ")");
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

    @Override
    public String savedString() {
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
    public void printDeleted() {
        if (!this.getStatus()) {
            System.out.println(Ui.straightLine + "\n  duke.Task deleted!\n    [D][ ] " + this.getDescription()
                    + "(by: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy"))  + " " + time + ")");
        } else {
            System.out.println(Ui.straightLine + "\n  duke.Task deleted!\n    [D][X] " + this.getDescription()
                    + "(by: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy"))  + " " + time + ")");
        }
    }
}
