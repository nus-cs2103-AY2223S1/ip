import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Event extends Task {

  private final LocalDate date;

  Event(String title, boolean status, String date)
    throws DateTimeParseException {
    super(title, status);
    this.date = LocalDate.parse(date);
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (at: " + this.date + ")";
  }
}
