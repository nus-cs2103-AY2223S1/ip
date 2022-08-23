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

    public DateTimeConverter(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    public String convert(String[] dateTime) {
        String dateIn = dateTime[0];
        LocalDate dateOut = LocalDate.parse(dateIn, dateFormatter);
        String date = dateOut.format(DateTimeFormatter
                .ofPattern("MMM d yyyy"));

        if (dateTime.length > 1) {
            try {
                String timeIn = dateTime[1];
                SimpleDateFormat SDF24H = new SimpleDateFormat("HHmm");
                SimpleDateFormat SDF12H = new SimpleDateFormat("h:mm a");
                Date time24H = SDF24H.parse(timeIn);
                date += (" " + SDF12H.format(time24H));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    public boolean isValidDate(String date) {
        try {
            this.dateFormatter.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
