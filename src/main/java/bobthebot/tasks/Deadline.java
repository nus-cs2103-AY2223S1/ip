package bobthebot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class representing deadlines, a type of task.
 * */
public class Deadline extends Task {
    private String by;

    /**
     * Constructor for deadlines.
     * @param description of the deadline.
     * @param by the deadline of the task.
     * */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * A method which changes the format of the date from YYYY-MM-DD to MMM D YYYY
     * Eg. Method will change 2022-12-12 to Dec 12 2022.
     * @param by Date to be changed.
     * @return String in format MMM D YYYY.
     * */
    public String changeByFormat(String by) {
        // split the date and the time
        String[] splitDeadline = by.split(" ");
        String givenDate = splitDeadline[0].trim();
        LocalDate outputDate = LocalDate.parse(givenDate);

        String time = splitDeadline[1].trim();
        String date = outputDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        return date + ", " + time;
    }

    /**
     * Returns a String representation of a deadline.
     * @return String representation of deadline.
     * */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + changeByFormat(this.by) + ")";
    }

    /**
     * Method which converts a deadline to a format for storage.
     * @return String representation of a deadline for storage.
     * */
    @Override
    public String toStorageFormat() {
        int done = isDone ? 1 : 0;
        String res = String.format("D | %d | %s | %s", done, taskName, by);
        return res;
    }
}
