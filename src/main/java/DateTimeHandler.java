import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHandler {
    public static final DateTimeFormatter receiveDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter returnDateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
    public static final DateTimeFormatter receiveTimeFormat = DateTimeFormatter.ofPattern("HHmm");
    public static final DateTimeFormatter returnTimeFormat = DateTimeFormatter.ofPattern("HH:mm");

    public static LocalDate formatDate(String strDate) {
        LocalDate date = LocalDate.parse(strDate, receiveDateFormat);
        return date;
    }

    public static LocalTime formatTime(String strTime) {
        LocalTime time = LocalTime.parse(strTime, receiveTimeFormat);
        return time;
    }
}
