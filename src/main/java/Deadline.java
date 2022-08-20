import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

  private final LocalDate date;

  Deadline(String title, boolean status, String date)
    throws DateTimeParseException {
    super(title, status);
    this.date = LocalDate.parse(date);
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + this.date + ")";
  }
}
