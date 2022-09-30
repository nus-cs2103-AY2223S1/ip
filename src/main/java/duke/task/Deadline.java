package duke.task;

import com.sun.prism.shader.DrawEllipse_Color_AlphaTest_Loader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

/**
 * Represents a task that has a deadline.
 */
public class Deadline extends Task implements Comparable<Deadline> {

    /** Deadline for this task */
    private LocalDate date;

    /**
     * Constructs a new deadline task with
     * given description and deadline.
     *
     * @param description of the task.
     * @param deadline of the task to be completed by.
     */
    public Deadline(String description, String deadline) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(deadline, formatter);
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + ")";
    }

    @Override
    public String toMemoryString() {
        return "D"
                + " | "
                + (this.isDone() ? "1" : "0")
                + " | "
                + this.getName()
                + " | "
                + this.date;
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public int compareTo(Deadline o) {
        return getDate().compareTo(o.getDate());
    }
}
