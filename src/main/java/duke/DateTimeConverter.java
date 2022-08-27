package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;


public class DateTimeConverter {
    protected static final DateTimeFormatter dateFormat = DateTimeFormatter
            .ofPattern("yyyy-MM-dd");
    protected static final DateTimeFormatter dateFormatPrint = DateTimeFormatter
            .ofPattern("MMM dd yyyy");


    public static LocalDate formatDate(String dateString){
            LocalDate date = LocalDate.parse(dateString,dateFormat);
            return date;
        }

    public static String formatString(LocalDate date){
            return date.format(dateFormatPrint);
        }
    }
