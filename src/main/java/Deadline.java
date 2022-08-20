import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
  public Deadline(String description, String by) throws DukeException {
    super(description);
    try {
      this.dateTime = LocalDateTime.parse(by, Ui.getInputFormatter());
    } catch (DateTimeParseException e) {
      try {
        this.dateTime = LocalDateTime.parse(by, Ui.getOutputFormatter());
      } catch (DateTimeParseException e2) {
        throw new DukeException("Invalid date format. Use yyyy-mm-dd HHmm.");
      }
    }
  }

  /**
   * Returns the description of the task, with a Deadline tag
   * @return Description of the task.
   */
  @Override
  public String toString() {
    return "[D]" + super.toString() + "(by: " + dateTime.format(Ui.getOutputFormatter()) + ")";
  }
}
