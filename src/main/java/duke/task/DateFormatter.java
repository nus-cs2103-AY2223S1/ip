package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class which manages formatting of date.
 */
public class DateFormatter {
    protected String dateInString;
    protected LocalDate dateAndTime;

    /**
     * Creates an instance of date formatter.
     * @param date string of date
     */
    public DateFormatter(String date) {
        dateInString = date;

        if (dateInString.contains("/")) {
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");

            try {
                dateAndTime = LocalDate.parse(dateInString, formatter1);
            } catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
            }
            try {
                dateAndTime = LocalDate.parse(dateInString, formatter2);
            } catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
            }

        } else if (dateInString.contains("-")) {
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            try {
                dateAndTime = LocalDate.parse(dateInString, formatter1);
            } catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
            }

            try {
                dateAndTime = LocalDate.parse(dateInString, formatter2);
            } catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public LocalDate formattedDate() {
        return dateAndTime;
    }
}
