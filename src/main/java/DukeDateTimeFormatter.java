import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DukeDateTimeFormatter {
    public static String format(LocalDate ld) {
        return ld.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
    }
}
