package poolsheen.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import poolsheen.PoolsheenException;

/**
 * Represents a Deadline task for Poolsheen to remember.
 */
public class Deadline extends Task {
    /**
     * A public constructor to initialise a Deadline task.
     *
     * @param description The details of the task.
     * @param time The time which the task must be done by.
     */

    public Deadline(String description, boolean isDone, String time) {
        super(description, isDone);
        this.setTime(time);
    }

    @Override
    public void setTime(String newTime) {
        //Ensure that the newTime input are made out of numbers.
        String[] arr = newTime.split("-");
        if (arr.length != 3) {
            throw new PoolsheenException(newTime, "deadline", "Enter an appropriate date and time format: YYYY-MM-DD");
        }
        for (String s : arr) {
            Integer.parseInt(s);
        }
        try {
            this.time = LocalDate.parse(newTime);
        } catch (DateTimeParseException e) {
            throw new PoolsheenException(newTime, "deadline", "Enter a valid date and time.");
        }
    }

    @Override
    public String[] toArr() {
        return new String[] {"D", this.getStatusIcon(), this.getDescription(), this.time.toString()};
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: "
                + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
