/**
 * A-Class to represent a task containing description and completion status.
 */
public class Task {
  protected final String description;
  protected boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  /**
   * Returns the completion status of the task
   * @return String representation of the completion status of the task
   */
  public String getStatusIcon() {
    return isDone
            ? "X" // done task marked as X
            : " ";
  }

  /**
   * Marks the task as completed
   */
  public void mark() {
    isDone = true;
  }

  /**
   * Unmarks the task as not completed
   */
  public void unmark() {
    isDone = false;
  }

  /**
   * Returns the description of the task.
   * @return Description of the task.
   */
  @Override
  public String toString() {
    return "[" + getStatusIcon() + "] " + description;
  }
}
