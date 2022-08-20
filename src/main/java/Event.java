import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Event extends Task {

  public static final String SYMBOL = "E";

  private final LocalDate date;

  Event(String title, boolean status, String date)
    throws DateTimeParseException {
    super(title, status);
    this.date = LocalDate.parse(date);
  }

  @Override
  String saveString() {
    return (
      SYMBOL +
      " | " +
      (this.status ? "1" : "0") +
      " | " +
      this.title +
      " /at " +
      this.date
    );
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (at: " + this.date + ")";
  }
}
