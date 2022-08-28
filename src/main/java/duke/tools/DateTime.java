package duke.tools;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Converter of date input from user to standardised "dd MMM yyyy" format.
 */
public class DateTime {
    private LocalDate date;

    /**
     * Creates DateTime object from user date input.
     * @param dateTime date input from user.
     */
    public DateTime(String dateTime) {

        //format of date received needs to be of the form 2022-08-27
        this.date = LocalDate.parse(dateTime);
    }

    /**
     * Returns the date in the form "dd MMM yyyy".
     * @return the date in the form "dd MMM yyyy".
     */
    public String getDate() {

        return this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    public static String convertDate(String date) {
        LocalDate newDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return newDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
