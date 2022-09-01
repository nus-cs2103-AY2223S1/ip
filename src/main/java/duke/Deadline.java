package duke;

import java.time.LocalDate;


public class Deadline extends Task{

    protected LocalDate deadline;

    public Deadline(String taskName, boolean isDone, LocalDate deadline) {
        super(taskName,isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.taskName + " (by: " +
                this.deadline.getDayOfWeek() + ", " +
                this.deadline.getMonth() + " " + this.deadline.getDayOfMonth() +
                " " + this.deadline.getYear() + " " + ")";
    }

    @Override
    public String toStore() {
        return "|D|" + "|" + this.getStatusIcon() + "| " + this.taskName + " (by: " +
                this.deadline.toString() + ")";
    }
}
