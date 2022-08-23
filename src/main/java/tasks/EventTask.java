package tasks;

/**
 * Tasks that start at a specific time and ends at a specific time
 */
public class EventTask extends Task {

  private String at;

  /**
   * Constructor for EventTask with description and isDone initialised to false
   * 
   * @param description Description of task details
   * @param by          When user requires the task to be done by
   */
  public EventTask(String description, String at) {
    super(description);
    this.at = at;
  }

  /**
   * Constructor for EventTask and isDone initialised
   * 
   * @param description Description of task details
   * @param by          When user requires the task to be done by
   * @param isDone      Whether task has been done
   */
  public EventTask(String description, String at, boolean isDone) {
    super(description, isDone);
    this.at = at;
  }

  /**
   * Returns String representation of EventTask
   *
   * @return String representation of EventTask
   */
  @Override
  public String toString() {
    return "[E]" + super.toString() + " (at: " + this.at + ")";
  }

  @Override
  public String toSaveString() {
    return "E " + super.toSaveString() + "|" + this.at;
  }
}
