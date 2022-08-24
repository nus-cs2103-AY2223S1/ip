import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateAndTime {

    private LocalDate date;

    DateAndTime(String date) {
        this.date = LocalDate.parse("2019-12-01");
    }

    @Override
    public String toString() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
