import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DukeDateTimeFormatter {

    public static String formatDisplay(LocalDate ld) {
        return ld.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
    }

    public static String formatStorage(LocalDate ld) {
        return ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
