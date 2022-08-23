package tasks;

import exception.DukeException;

/**
 * Tasks that need to be done before a specific date/time
 */
public class DeadlineTask extends Task {

  private String by;

  /**
   * Constructor for DeadlineTask with description and isDone initialised to false
   * 
   * @param description Description of task details
   * @param by          When user requires the task to be done by
   */
  public DeadlineTask(String description, String by) {
    super(description);
    this.by = by;
  }

  /**
   * Constructor for DeadlineTask and isDone initialised
   * 
   * @param description Description of task details
   * @param by          When user requires the task to be done by
   * @param isDone      Whether task has been done
   */
  public DeadlineTask(String description, String by, boolean isDone) {
    super(description, isDone);
    this.by = by;
  }

  /**
   * Returns String representation of Deadline
   *
   * @return String representation of Deadline
   */
  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + this.by + ")";
  }

  @Override
  public String toSaveString() {
    return "D " + super.toSaveString() + "|" + this.by;
  }
}
