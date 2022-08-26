package anya.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDateTime completeBy;

    public Deadline(String name, LocalDateTime completeBy) {
        super(name);
        this.completeBy = completeBy;
    }

    /**
     * {@inheritDoc}
     * The type of task, D, is appended to the front of the String.
     *
     * @return D, the status icon, the name of task, and the deadline to complete the task in a String.
     */
    @Override
    public String toString() {
        String dateTime = completeBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy',' hh'.'mma"));
        return "[D]" + super.toString() + " (by: " + dateTime + ")";
    }

    /**
     * {@inheritDoc}
     * D represents a Deadline.
     * 0 represents an incomplete task while 1 represents a completed task.
     * Date is represented in dd/mm/yy format and time is represented in 24-hour notion hh:mm.
     *
     * @return D, an integer representing whether the task is completed, the task name,
     * the date and time to complete the task by.
     */
    @Override
    public String toSave() {
        String doneVar = super.isDone ? "1" : "0";
        String dateTime = completeBy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        return "D | " + doneVar + " | " + super.name + " | " + dateTime;
    }
}
