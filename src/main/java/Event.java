import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class represents tasks that start at a specific time and ends at a specific time.
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Constructor for creating an event.
     * @param description Task description from user input.
     * @param dateTimeInput Event date/time from user input.
     * @throws InvalidDateException If date entered by user is not in the specified format.
     */
    public Event(String description, String dateTimeInput) throws InvalidDateException{
        super(description);

        try {
            // Adapted from 3. LocalDate parse() method and 4. LocalDateTime parse() method of
            // https://codegym.cc/groups/posts/parse-methods-in-java
            // and subsection "Using dates/times in Java" of section "Duke Level-8: Dates and Times" from
            // https://nus-cs2103-ay2223s1.github.io/website/schedule/week3/project.html
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            at = LocalDateTime.parse(dateTimeInput, formatter);

        } catch (DateTimeParseException e) {
            throw new InvalidDateException("You have keyed in an invalid date and time!\n"
                    + "Please specify date and time in the format: dd/MM/yyyy HHmm\n"
                    + "E.g. 24/08/2022 2359");

        }
    }

    /**
     * Displays the task type of event as E.
     * @return E.
     */
    @Override
    public String taskType() {
        return "E";
    }

    /**
     * Displays the event with its type, status (done or undone), description and time.
     * @return Task type, status, description and time.
     */
    @Override
    public String toString() {
        // Adapted from 3. LocalDate parse() method and 4. LocalDateTime parse() method of
        // https://codegym.cc/groups/posts/parse-methods-in-java
        // and subsection "Using dates/times in Java" of section "Duke Level-8: Dates and Times" from
        // https://nus-cs2103-ay2223s1.github.io/website/schedule/week3/project.html
        String newDateTime = at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return String.format("%s (at: %s)", super.toString(), this.at);
    }

}
