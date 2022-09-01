import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormatter {
    protected String dateInString;
    protected LocalDate dateAndTime;

    public DateFormatter(String date) {
        dateInString = date;

        if (dateInString.contains("/")) {
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");

            try {
                dateAndTime = LocalDate.parse(dateInString, formatter1);
            } catch (DateTimeParseException e) {}

            try {
                dateAndTime = LocalDate.parse(dateInString, formatter2);
            } catch (DateTimeParseException e) {}

        } else if (dateInString.contains("-")) {
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            try {
                dateAndTime = LocalDate.parse(dateInString, formatter1);
            } catch (DateTimeParseException e) {}

            try {
                dateAndTime = LocalDate.parse(dateInString, formatter2);
            } catch (DateTimeParseException e) {}
        }
    }

    public LocalDate formattedDate() {
        return dateAndTime;
    }
}
