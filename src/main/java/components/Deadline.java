package components;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents deadline tasks.
 */
public class Deadline extends Task {

  protected String by;
  protected LocalDate by2;
  protected String time;

  public Deadline(String description, String by) {
    super(description);
    this.by = by;
  }

  public Deadline(String description, LocalDate by2) {
    super(description);
    this.by2 = by2;

  }

  /**
   * Returns string representation of Deadline.
   *
   * @return string representation.
   */
  @Override
  public String toString() {
    if (this.by != null) {
      return "[D]" + super.toString() + " (by: " + by + ")";
    } else {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
      return "[D]" + super.toString() + " (by: " + by2.format(formatter) + ")";
    }
  }
}
