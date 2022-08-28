package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline Class to represent a class that will store Deadline Objects
 * @author Ashiqur Rahman A02030107Y
 */
public class Deadline extends Task {
    protected String deadlineDay;
    protected LocalDate deadlineDateTime;

    /**
     * Constructor to create Deadline object
     * @param description Details of Task
     * @param deadlineDay Deadline of Task in yyyy-MM-dd format
     */
    public Deadline (String description, String deadlineDay) {
        super(description);
        this.deadlineDay = deadlineDay.replaceAll(" ", "");
        this.stringToDate();
    }

    /**
     * Method to convert deadline string to LocalDate object and to initialise deadlineDate attribute
     */
    public void stringToDate() {
        try {
            DateTimeFormatter convertFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate deadlineDate = LocalDate.parse(this.deadlineDay, convertFormatter);
            this.deadlineDateTime = deadlineDate;
        } catch (Exception err) {
            DukeException.DateTimeFormatErrorMessage();
            System.out.println(err);
        }
    }

    /**
     * Method to convert LocalDate object to required format, MMM d yyyy
     * @return String of deadline in MMM d yyy format
     */
    public String modifedDate() {
        DateTimeFormatter printFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String modifiedString = this.deadlineDateTime.format(printFormatter);
        return modifiedString;
    }

    @Override
    public String toString() {
        String s = String.format("[D]%s (by: %s)", super.toString(), this.modifedDate());
        return s;
    }
}
