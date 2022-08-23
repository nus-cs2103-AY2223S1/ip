package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Tasks that need to be done before a specific date/time
 */
public class Deadline extends Task {

  private LocalDate by;

  /**
   * Constructor for deadline task
   * 
   * @param description Description of task details
   * @param by          When user requires the task to be done by
   */
  public Deadline(String description, LocalDate by) {
    super(description);
    this.by = by;
  }

  /**
   * Returns String representation of Deadline
   *
   * @return String representation of Deadline
   */
  @Override
  public String toString() {
    return "[D]" + super.toString() + "(by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
  }
}
