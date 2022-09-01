package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    
    private static final String DEADLINE_LETTER = "D";


    private static LocalDateTime parseDateAndTimeString(String dateAndTime) {

        // dateAndTime is of the form "yyyy-mm-dd hh:mm"
        // Therefore, the first token is the date and the second token is the time

        // Split string around whitespaces
        String[] tokens = dateAndTime.split("\\s");
        
        LocalDate date = LocalDate.parse(tokens[0]);
        LocalTime time = LocalTime.parse(tokens[1]);

        return LocalDateTime.of(date, time);
    }


    // Deadline is of the form "deadline description /by dateAndTime"
    protected final LocalDateTime dateAndTime;


    // Constructors
    public Deadline(String description, String dateAndTimeString) {
        this(description, false, parseDateAndTimeString(dateAndTimeString));
    }


    public Deadline(String description, boolean isDone, LocalDateTime dateAndTimeObject) {
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


    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", DEADLINE_LETTER, super.toString(), dateAndTimeToString());
    }

}
