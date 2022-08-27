package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;


public class DateTimeConverter {
    protected static final DateTimeFormatter dateFormat = DateTimeFormatter
            .ofPattern("yyyy-MM-dd");
    protected static final DateTimeFormatter dateFormatPrint = DateTimeFormatter
            .ofPattern("MMM dd yyyy");

    /**
     * Returns LocalDate version of a date
     * that is a String
     *
     * @param dateString String of the Date
     * @return LocalDate.
     */
    public static LocalDate formatDate(String dateString){
            LocalDate date = LocalDate.parse(dateString,dateFormat);
            return date;
        }

    /**
     * Returns String version of a date
     * that is in LocalDate format
     *
     * @param date LocalDate date
     * @return String.
     */

    public static String formatString(LocalDate date){
            return date.format(dateFormatPrint);
        }
    }
