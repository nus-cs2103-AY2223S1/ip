package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class formatDate {
    LocalDate formatDate;

    /**
     *
     * @param str pass in string of the date in the format of yyyy-MM-dd
     */
    public formatDate(String str) {
        this.formatDate = LocalDate.parse(str);
    }

    /**
     *
     * @return switch String format from yyy-MM-dd to MMM d yyyy
     */
    @Override
    public String toString() {
        return this.formatDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

}
