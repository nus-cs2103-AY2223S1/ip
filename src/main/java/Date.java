import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Date {
  private LocalDate date;

  public Date(String date) throws DateTimeParseException {
    this.date = LocalDate.parse(date);
  }

  @Override
  public String toString() {
    return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
  }
}
