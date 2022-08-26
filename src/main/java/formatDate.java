import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class formatDate {
    LocalDate formatDate;

    public formatDate(String str) {
        this.formatDate = LocalDate.parse(str);
    }

    @Override
    public String toString() {
        return this.formatDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

}
