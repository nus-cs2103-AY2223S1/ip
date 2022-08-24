import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class represents tasks that need to be done before a specific date/time.
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for creating a deadline.
     * @param description Task description from user input.
     * @param dateTimeInput Date/time as deadline from user input.
     * @throws InvalidDateException If date entered by user is not in the specified format.
     */

    public Deadline(String description, String dateTimeInput) throws InvalidDateException {
        super(description);

        try {
            // Adapted from 3. LocalDate parse() method and 4. LocalDateTime parse() method of
            // https://codegym.cc/groups/posts/parse-methods-in-java
            // and subsection "Using dates/times in Java" of section "Duke Level-8: Dates and Times" from
            // https://nus-cs2103-ay2223s1.github.io/website/schedule/week3/project.html
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            by = LocalDateTime.parse(dateTimeInput, formatter);

        } catch (DateTimeParseException e) {
            throw new InvalidDateException("You have keyed in an invalid date and time!\n"
                    + "Please specify date and time in the format: dd/MM/yyyy HHmm\n"
                    + "E.g. 24/08/2022 2359");

        }
    }

    /**
     * Displays the task type of deadline as D.
     * @return D.
     */
    @Override
    public String taskType() {
        return "D";
    }

    /**
     * Displays the deadline with its type, status (done or undone), description and date/time.
     * @return Task type, status, description and date/time.
     */
    @Override
    public String toString() {
        // Adapted from 3. LocalDate parse() method and 4. LocalDateTime parse() method of
        // https://codegym.cc/groups/posts/parse-methods-in-java
        // and subsection "Using dates/times in Java" of section "Duke Level-8: Dates and Times" from
        // https://nus-cs2103-ay2223s1.github.io/website/schedule/week3/project.html
        String newDateTime = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));

        return String.format("%s (by: %s)", super.toString(), newDateTime);
    }

}
