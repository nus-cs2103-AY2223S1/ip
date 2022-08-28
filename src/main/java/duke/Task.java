package duke;

/**
 * Represents a task with its description and
 * its completion status
 *
 * @author  Gerald Teo Jin Wei
 * @version 0.1
 * @since   2022-08-28
 */
public class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public Task(String description, boolean isDone) {
    this.description = description;
    this.isDone = isDone;
  }

  /**
   * This method marks the task as completed
   */
  public void mark(){
    this.isDone = true;
  }

  /**
   * This method unmarks the task as not completed
   */
  public void unmark(){
    this.isDone = false;
  }

  /**
   * This method returns the task's completion status as a string
   * @return String This returns "X" when completed and " " when not completed
   */
  public String getStatusIcon() {
    return (isDone ? "X" : " ");
  }

  /**
   * This method returns the task's completion status and description
   * @return String This returns the string of the task in the specified format
   */
  public String toString() {
    return "[" + this.getStatusIcon() + "] " + this.description;
  }

}
