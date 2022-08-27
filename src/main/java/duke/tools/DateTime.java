package duke.tools;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTime {
    private LocalDate date;

    public DateTime(String dateTime) {

        //format of date received needs to be of the form 2022-08-27
        this.date = LocalDate.parse(dateTime);
    }

    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }
}
