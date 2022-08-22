import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Parser {

    protected static final String[] DATEPATTERNS = {"yyyy-MM-d HH:mm", "yyyy/MM/d HH:mm", "yyyy MM d HH:mm",
            "d-MM-yyyy HH:mm", "d/MM/yyyy HH:mm", "d MM yyyy HH:mm",
            "yyyy-MMM-d HH:mm", "yyyy/MMM/d HH:mm", "yyyy MMM d HH:mm",
            "d-MMM-yyyy HH:mm", "d/MMM/yyyy HH:mm", "d MMM yyyy HH:mm",
            "yyyy-MMMM-d HH:mm", "yyyy/MMMM/d HH:mm", "yyyy MMMM d HH:mm",
            "d-MMMM-yyyy HH:mm", "d/MMMM/yyyy HH:mm", "d MMMM yyyy HH:mm"};
    public Parser() {
    }

    public LocalDateTime parseTime(String date) throws DukeException {
        for (String pattern : DATEPATTERNS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDateTime.parse(date, formatter);
            } catch (Exception e) {

            }
        }
        throw DukeException.DukeInvalidDateFormatException();
    }
}