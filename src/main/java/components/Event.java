package components;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents event tasks.
 */
public class Event extends Task {

  protected String at;
  protected LocalDate at2;


  public Event(String description, String at) {
    super(description);
    this.at = at;
  }

  public Event(String description, LocalDate at2) {
    super(description);
    this.at2 = at2;
  }

  /**
   * Returns string representation of Event.
   *
   * @return string representation.
   */
  @Override
  public String toString() {

    if (this.at != null) {
      return "[E]" + super.toString() + " (at: " + at + ")";
    } else {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
      return "[E]" + super.toString() + " (at: " + at2.format(formatter) + ")";
    }
  }
}
