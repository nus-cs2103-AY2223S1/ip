package duke;

/**
 * Represents a deadline with its description,
 * completion status and its due date.
 *
 * @author  Gerald Teo Jin Wei
 * @version 0.1
 * @since   2022-08-28
 */
public class Deadline extends  Task{
  protected String by;
  public Deadline(String description,String date, boolean isDone) {
    super(description,isDone);
    this.by = date;
  }
  public Deadline(String description,String date) {
    super(description);
    this.by = date;
  }

  /**
   * Returns the task's type, completion status, description and due date
   * @return String This returns the string of the deadline in the specified format
   */
  public String toString() {
    return "[D][" + this.getStatusIcon() + "] " + this.description + " (by: " + by + ")";
  }
}
