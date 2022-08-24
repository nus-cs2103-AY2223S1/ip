import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a Task that accepts a task description and a deadline (dd-MM-yyyy)
 */
public class Deadline extends Task {

  protected String by;
  protected LocalDate byDate;


  /**
   * Constructor for Deadline
   * @param description description of the task
   * @param by date in the format of dd-MM-yyyy
   */
  public Deadline(String description, String by) {
    super(description);
    this.by = by;
    this.byDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
  }

  @Override
  public String toString() {
    return "[D]" + super.toString()
        + "(by: " + byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
  }
}