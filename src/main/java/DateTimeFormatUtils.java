import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeFormatUtils {

    private final static DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final static DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");

    public static LocalDateTime parseDate(String date) throws DukeException {
        try {
            LocalDateTime inputDate = LocalDateTime.parse(date, INPUT_FORMAT);
            return inputDate;
        } catch (DateTimeParseException e) {
            throw new DukeException("\tDate Format Police here!!" +
                    "\n\tYour deadline has to be in YYYY-MM-DD HHmm format!!");
        }
    }

    public static LocalDateTime[] parseDuration(String date) throws DukeException {
        try {
            String[] input = date.split(" to ");
            LocalDateTime startDate = LocalDateTime.parse(input[0], INPUT_FORMAT);
            LocalDateTime endDate = LocalDateTime.parse(input[1], INPUT_FORMAT);
            return new LocalDateTime[] {startDate, endDate};
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("\tDate Format Police here!!" +
                    "\n\tYour deadline has to be in <YYYY-MM-DD HHmm to YYYY-MM-DD HHmm> format!!");
        }
    }

    public static String printDate(LocalDateTime date) {
        String output = date.format(OUTPUT_FORMAT);
        return output;
    }
}
