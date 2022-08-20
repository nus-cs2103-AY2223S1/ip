import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

  public static final String SYMBOL = "D";

  private final LocalDate date;

  Deadline(String title, boolean status, String date)
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
      " /by " +
      this.date
    );
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + this.date + ")";
  }
}
