package duke.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

public class DateTimeConverter {
    /**
     * Main class to convert date and time from "yyyy-mm-dd"
     * eg. (2022-2-2 1800 -> Feb 2 2022 6:00 PM)
     */
    private DateTimeFormatter dateFormatter;

    private SimpleDateFormat SDF24H = new SimpleDateFormat("HHmm");
    private SimpleDateFormat SDF12H = new SimpleDateFormat("h:mm a");

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
                Date time24H = SDF24H.parse(timeIn);
                date += (" " + SDF12H.format(time24H));
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
