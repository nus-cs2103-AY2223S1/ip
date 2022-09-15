package bobthebot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class representing deadlines, a type of task.
 */
public class Deadline extends Task {
    private String dueDate;

    /**
     * Constructs an instance of a deadlines.
     *
     * @param description of the deadline.
     * @param dueDate the deadline of the task.
     * */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Changes the format of the date from YYYY-MM-DD to MMM D YYYY
     * Eg. Method will change 2022-12-12 to Dec 12 2022.
     *
     * @return String in format MMM D YYYY.
     */
    public String changeDueDateFormat() {
        // split the date and the time
        String[] splitDeadline = dueDate.split(" ");
        String givenDate = splitDeadline[0].trim();
        LocalDate outputDate = LocalDate.parse(givenDate);

        String time = splitDeadline[1].trim();
        String date = outputDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        return date + ", " + time;
    }

    /**
     * Checks if the deadline is within a week of its deadline. Returns True if the deadline is within
     *      a week of its deadline and False otherwise.
     *
     * @return Boolean representing if a task is within a week of its deadline.
     */
    public Boolean isWithinWeekOfDeadline() {
        String[] splitEvent = dueDate.split(" ");
        String dueDate = splitEvent[0].trim();
        LocalDate formattedDueDate = LocalDate.parse(dueDate);

        LocalDate today = LocalDate.now();
        LocalDate oneWeekLater = today.plusWeeks(1);

        return formattedDueDate.isBefore(oneWeekLater);
    }

    /**
     * Returns a String representation of a deadline.
     *
     * @return String representation of deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.changeDueDateFormat() + ")";
    }

    /**
     * Converts a deadline to a format for storage.
     *
     * @return String representation of a deadline for storage.
     */
    @Override
    public String toStorageFormat() {
        int doneStatus = isDone ? 1 : 0;
        String deadlineString = String.format("D | %d | %s | %s", doneStatus, taskName, dueDate);
        return deadlineString;
    }
}
