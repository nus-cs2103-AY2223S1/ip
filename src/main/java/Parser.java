import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
  public Parser(){}

  public static String parseDate(String dateAndTime) {
    LocalDate d = LocalDate.parse(dateAndTime);
    String date = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    return date;
  }

}
