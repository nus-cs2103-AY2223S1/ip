package duke.task;

import duke.DateTime;

/**
 * A representation of Deadline
 */
public class Deadline extends Task {
  private DateTime dateTime;

  /**
   * Initialises Deadline
   *
   * @param description
   * @param isDone
   * @param by
   */
  public Deadline(String description, Boolean isDone, String by) {
    super(description, isDone);
    this.dateTime = new DateTime(by);
  }

  /**
   * Initialises Deadline
   *
   * @param description
   * @param by
   */
  public Deadline(String description, String by) {
    super(description);
    this.dateTime = new DateTime(by);
  }

  @Override
  public String toString() {
    return String.format("[D]%s (by: %s)", super.toString(), this.dateTime.toString());
  }

  @Override
  public String[] getPrintRepresentation() {
    String[] strArray = super.getPrintRepresentation();
    return new String[] { "Deadline", strArray[1], strArray[2], this.dateTime.toString() };
  }
}
