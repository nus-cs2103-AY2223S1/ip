package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

  public static final String SYMBOL = "D";

  private final LocalDate date;

  public Deadline(String title, boolean status, String date)
    throws DateTimeParseException {
    super(title, status);
    this.date = LocalDate.parse(date);
  }

  @Override
  public String encode() {
    return (
      SYMBOL +
      " | " +
      (this.status ? "1" : "0") +
      " | " +
      this.title +
      " | " +
      this.date
    );
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + this.date + ")";
  }
}
