package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task of a deadline.
 */
public class Deadline extends Task{
    String by;
    LocalDate byDate = null;
    String type;

    /**
     * Creates a deadline object.
     * @param name Name of the deadline task.
     * @param isDone Status of whether the task is done.
     * @param by String of a deadline inputted by the task.
     */
    Deadline(String name, boolean isDone, String by){
        super(name, isDone);
        this.by = by;
        this.type = "[D]";
    }

    /**
     * Creates a deadline object.
     * @param name Name of the deadline task.
     * @param isDone Status of whether the task is done.
     * @param byDate LocateDate of a deadline inputted by the task.
     */
    Deadline(String name, boolean isDone, LocalDate byDate) {
        super(name,isDone);
        this.byDate = byDate;
        this.type = "[D]";
    }

    /**
     * Represents a Deadline task as a String.
     * @return String representation that include the type, status(marked), name and date of the deadline task.
     */
    @Override
    public String toString() {
        String s = this.type + super.getStatus() + " (by: ";
        if (this.byDate != null) {
            return s + this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return s + this.by + ")";
        }
    }
}