package duke.events;

/**
 * Deadline is an extension of the Task class
 * It contains a due date for the finished task
 * And prints it out in a readable format via the toString method
 */

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Deadline extends Task {
    protected LocalDate by;
    private String unformattedDate;
    private static final String TYPE = "[D]";

    private static final int NUM_ELEMENTS_EXPECTED = 4;

    public Deadline(String description, String notFormattedDate) {
        super(description);
        this.unformattedDate = notFormattedDate;
        by = LocalDate.parse(notFormattedDate);
    }

    public Deadline(boolean isDone, String description, String unformattedDate) {
        super(isDone, description);
        this.unformattedDate = unformattedDate;
        by = LocalDate.parse(unformattedDate);
    }


    public static Deadline readTask(String[] descriptionArgs) {
        assert descriptionArgs.length == NUM_ELEMENTS_EXPECTED
                : "Save data was not parsed correctly, incorrect number of elements read";
        boolean isDone = descriptionArgs[1].equals("0");
        String description = descriptionArgs[2];
        String unformattedDate = descriptionArgs[3];
        return new Deadline(isDone, description, unformattedDate);

    }

    @Override
    public String savableString() {
        return "D" + super.savableString() + "//" + unformattedDate;
    }





    private String customFormatter(LocalDate ld) {
        return ld.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public boolean compareDate(String date) {
        LocalDate formattedDate = LocalDate.parse(date);
        return formattedDate.isEqual(by);
    }

    @Override
    public String toString() {
        return TYPE + super.toString() + "(by: " + customFormatter(by) + ")";
    }
}