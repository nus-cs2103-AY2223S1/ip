package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline type of Task object.
 * A Deadline type of Task needs to be completed before a specific date and time.
 */
public class Deadline extends Task {
    
    private static final String DEADLINE_LETTER = "D";


    /**
     * Parse the specified dateAndTime string into a LocalDateTime object.
     * 
     * @param dateAndTime String containing the date and time in the format: yyyy-mm-dd hh:mm
     * @return LocalDateTime object representing the specified date and time.
     */
    private static LocalDateTime parseDateAndTimeString(String dateAndTime) {

        // dateAndTime is of the form "yyyy-mm-dd hh:mm"
        // Therefore, the first token is the date and the second token is the time

        // Split string around whitespaces
        String[] tokens = dateAndTime.split("\\s");
        
        LocalDate date = LocalDate.parse(tokens[0]);
        LocalTime time = LocalTime.parse(tokens[1]);

        return LocalDateTime.of(date, time);
    }


    /** LocalDateTime object to store the date and time that the Deadline object should be completed */
    protected final LocalDateTime dateAndTime;


    /**
     * Creates a new Deadline object.
     * 
     * @param description Description of the Deadline object.
     * @param dateAndTimeString String containing the date and time in the format: yyyy-mm-dd hh:mm
     */
    public Deadline(String description, String dateAndTimeString) {
        this(description, false, parseDateAndTimeString(dateAndTimeString));
    }


    private Deadline(String description, boolean isDone, LocalDateTime dateAndTimeObject) {
        super(description, isDone);
        this.dateAndTime = dateAndTimeObject;
    }

    
    @Override
    public Deadline markTask() {
        return new Deadline(description, true, dateAndTime);
    }
    
    
    @Override
    public Deadline unmarkTask() {
        return new Deadline(description, false, dateAndTime);
    }

    
    private String dateAndTimeToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy 'at' hh:mm a");
        return this.dateAndTime.format(formatter);
    }


    /**
     * Returns the string representation of the Deadline object.
     * 
     * @return String representation of the Deadline object.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", DEADLINE_LETTER, super.toString(), dateAndTimeToString());
    }

}
