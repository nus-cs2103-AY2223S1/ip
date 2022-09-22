package duke.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * Main class to convert date and time from "yyyy-mm-dd"
 * eg. (2022-2-2 1800 -> Feb 2 2022 6:00 PM)
 */
public class DateTimeConverter {

    private DateTimeFormatter dateFormatter;

    private SimpleDateFormat sdf24h = new SimpleDateFormat("HHmm");
    private SimpleDateFormat sdf12h = new SimpleDateFormat("h:mm a");

    /**
     * Constructor for the DateTimeConverter class
     *
     * @param dateFormatter the format in which the input date is in
     */
    public DateTimeConverter(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    /**
     * Method to convert dateTime from "yyyy-mm-dd"
     * eg. (2022-2-2 1800 -> Feb 2 2022 6:00 PM)
     *
     * @param dateTime the date and time to be converted
     * @return the converted date and time format
     */
    public String convert(String[] dateTime) {
        String dateIn = dateTime[0];
        LocalDate dateOut = LocalDate.parse(dateIn, dateFormatter);
        String date = dateOut.format(DateTimeFormatter
                .ofPattern("MMM d yyyy"));

        if (dateTime.length > 1) {
            try {
                String timeIn = dateTime[1];
                Date time24H = sdf24h.parse(timeIn);
                date += (" " + sdf12h.format(time24H));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    /**
     * Method to check if the date given is valid
     *
     * @param date the date to be converted
     * @return boolean stating whether the date is valid
     */
    public boolean isValidDate(String date) {
        try {
            this.dateFormatter.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
