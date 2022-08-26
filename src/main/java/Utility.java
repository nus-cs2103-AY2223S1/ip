import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utility {

    public static LocalDateTime dateParser(String dateTime) {
        String[] splitDateTime = dateTime.trim().split(" ");
        String[] dateArray = splitDateTime[0].split("/");
        String time = splitDateTime[1];

        return LocalDateTime.of(
                Integer.parseInt(dateArray[2]),
                Integer.parseInt(dateArray[1]),
                Integer.parseInt(dateArray[0]),
                Integer.parseInt(time.substring(0, 2)),
                Integer.parseInt(time.substring(2, 4))
        );
    }

    public static String dateTimeToString(LocalDateTime dt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return dt.format(formatter);
    }

}
