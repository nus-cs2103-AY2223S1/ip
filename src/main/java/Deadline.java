import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
  public Deadline(String description, String by) {
    super(description);
    try {
      this.dateTime = LocalDateTime.parse(by, Duke.getFormatter());
    } catch (DateTimeParseException e) {
      System.out.println(new DukeException("Invalid date format. Use yyyy-mm-dd HHmm.").getMessage());
    }
  }

  /**
   * Returns the description of the task, with a Deadline tag
   * @return Description of the task.
   */
  @Override
  public String toString() {
    return "[D]" + super.toString() + "(by: " + dateTime.format(DateTimeFormatter.ofPattern("HH:mm, E, MMM dd yyyy")) + ")";
  }
}
