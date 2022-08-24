package Duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {




        public static LocalDate stringToDate(String string) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate myDateObj = LocalDate.parse(string, formatter);
            return myDateObj;
        }


        public static String dateToString(LocalDate date) {
            return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }

        public static String displayDate(LocalDate date) {
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

