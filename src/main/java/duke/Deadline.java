package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

<<<<<<< HEAD:src/main/java/duke/Deadlines.java
/**
 * Represents a task of a deadline.
 */
public class Deadlines extends Task{
=======
public class Deadline extends Task{
>>>>>>> branch-A-CodingStandard:src/main/java/duke/Deadline.java
    String by;
    LocalDate byDate = null;
    String type;

<<<<<<< HEAD:src/main/java/duke/Deadlines.java
    /**
     * Creates a deadline object.
     * @param name Name of the deadline task.
     * @param isDone Status of whether the task is done.
     * @param by String of a deadline inputted by the task.
     */
    Deadlines(String name, boolean isDone, String by){
=======
    Deadline(String name, boolean isDone, String by) {
>>>>>>> branch-A-CodingStandard:src/main/java/duke/Deadline.java
        super(name, isDone);
        this.by = by;
        this.type = "[D]";
    }

<<<<<<< HEAD:src/main/java/duke/Deadlines.java
    /**
     * Creates a deadline object.
     * @param name Name of the deadline task.
     * @param isDone Status of whether the task is done.
     * @param byDate LocateDate of a deadline inputted by the task.
     */
     Deadlines(String name, boolean isDone, LocalDate byDate) {
=======
     Deadline(String name, boolean isDone, LocalDate byDate) {
>>>>>>> branch-A-CodingStandard:src/main/java/duke/Deadline.java
        super(name,isDone);
        this.byDate = byDate;
        this.type = "[D]";
    }

<<<<<<< HEAD:src/main/java/duke/Deadlines.java
    /**
     * Represents a Deadline task as a String.
     * @return String representation that include the type, status(marked), name and date of the deadline task.
     */
=======
>>>>>>> branch-A-CodingStandard:src/main/java/duke/Deadline.java
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
