import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class formatDate {
    LocalDate formattedDate;

    public formatDate(String date) {
        this.formattedDate = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return this.formattedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
