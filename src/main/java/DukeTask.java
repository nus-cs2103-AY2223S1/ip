/**
 * Encapsulates a task stored in Apollo.
 *
 * @author Kartikeya
 */
public class DukeTask {
  // Description of task
  private final String description;

  // Indicates if the task has been completed
  private boolean isDone;

  public DukeTask(String description) {
    this.description = description;
    this.isDone = false;
  }

  /**
   * Returns status of task completion.
   *
   * @return String signifying status of task completion
   */
  public String getStatusIcon() {
    return (isDone ? "X" : " "); // mark done task with X
  }

  /**
   * Marks a task as complete and returns a string signifying this.
   *
   * @return String signifying task completion
   */
  public String markAsDone() {
    isDone = true;
    return "Good job! This task has been completed:\n  "
        + this;
  }

  /**
   * Marks a task as not complete and returns a string signifying this.
   *
   * @return String signifying incomplete task
   */
  public String markAsNotDone() {
    isDone = false;
    return "Whoops! This task is now yet to be completed:\n  "
        + this;
  }

  @Override
  public String toString() {
    return "[" + getStatusIcon() + "] " + description;
  }
}
