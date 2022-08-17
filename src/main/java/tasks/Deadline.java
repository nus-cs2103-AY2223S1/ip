package tasks;

/**
 * Tasks that need to be done before a specific date/time
 */
public class Deadline extends Task {

  private String by;

  /**
   * Constructor for deadline task
   * @param description Description of task details
   * @param by When user requires the task to be done by
   */
  public Deadline(String description, String by) {
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
    return "[D]" + super.toString() + "(by:" + this.by + ")";
  }
}
