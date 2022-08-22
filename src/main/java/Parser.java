import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Parser {

    public Parser() {
    }

    public LocalDate parseDate(String date) throws DukeException {
        String[] datePatterns = {"yyyy-MM-d", "yyyy/MM/d", "yyyy MM d", "d-MM-yyyy", "d/MM/yyyy", "d MM yyyy",
                "yyyy-MMM-d", "yyyy/MMM/d", "yyyy MMM d", "d-MMM-yyyy", "d/MMM/yyyy", "d MMM yyyy",
                "yyyy-MMMM-d", "yyyy/MMMM/d", "yyyy MMMM d", "d-MMMM-yyyy", "d/MMMM/yyyy", "d MMMM yyyy"};
        for (String pattern : datePatterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDate.parse(date, formatter);
            } catch (Exception e) {

            }
        }
        throw DukeException.DukeInvalidDateFormatException();
    }
}