package duke.task;
import java.time.LocalDateTime;

/**
 * Class to encapsulate a Task with deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for Deadline.
     *
     * @param desc The String description of the deadline task.
     * @param date The due date for the deadline task.
     */
    public Deadline(String desc, LocalDateTime date) {
        super(desc);
        deadline = date;
    }

    private String getDeadlineString() {
        int day = this.deadline.getDayOfMonth();
        int month = this.deadline.getMonthValue();
        String dayString = day < 10 ? String.format("0%d", day) : String.format("%d", day);
        String monthString = month < 10 ? String.format("0%d", month) : String.format("%d", month);
        int year = this.deadline.getYear();
        int time = this.deadline.getHour() * 100 + this.deadline.getMinute();
        return String.format("%s/%s/%d %d", dayString, monthString, year, time);

    }

    /**
     * Converts task to String in format for output file.
     *
     * @return The task description for output text file.
     */
    @Override
    public String toFileFormat() {
        return "D|" + super.toFileFormat() + "|" + this.getDeadlineString();
    }

    /**
     * Returns String representation of task.
     * Format is for output during program runtime.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDeadlineString() + ")";
    }

}
