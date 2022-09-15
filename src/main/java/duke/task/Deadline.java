package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class is a subclass of Task that has a due date and an optional due time.
 */
public class Deadline extends Task {
    
    /**
     * Stores the due date of the Deadline task.
     */
    protected LocalDate dueDate;
    
    /**
     * Stores the due time (if any) of the Deadline task.
     */
    protected LocalTime dueTime;

    /**
     * Constructor of Deadline class when there is no due time for the Deadline task.
     * @param description Description of the Deadline task.
     * @param dueDate Due date of the Deadline task.
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = LocalDate.parse(dueDate);
        this.dueTime = null;
    }

    /**
     * Overloaded constructor of Deadline class when there is a due time for the Deadline task.
     * @param description Description of the Deadline class.
     * @param dueDate Due date of the Deadline task.
     * @param dueTime Due time of the Deadline task.
     */
    public Deadline(String description, String dueDate, String dueTime) {
        super(description);
        this.dueDate = LocalDate.parse(dueDate);
        this.dueTime = LocalTime.parse(dueTime);
    }

    /**
     * Getter for the due date and due time (if any) of the Deadline task.
     */
    public String getDue() {
        if (dueTime == null) {
            return this.dueDate.toString();
        } else {
            return this.dueDate + " " + this.dueTime;
        }
    }

    /**
     * Returns a String literal "D" for the Task type.
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Overrides the parent class Task's toString() method to include its Task type, due date and due time.
     */
    @Override
    public String toString() {
        if (dueTime == null) {
            return "[D]" + super.toString() + " (by: "
                    + dueDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: "
                    + dueDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + dueTime + ")";
        }
    }
}
