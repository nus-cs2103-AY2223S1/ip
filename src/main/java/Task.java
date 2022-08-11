public class Task {
  private final String description;
  private boolean isDone;

  /**
   * Constructor that instantiates a task instance.
   * @param description - Text provided on creation of the task
   */
  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  /**
   * Marks task as done if task is currently undone.
   * @return boolean representing success of operation.
   */
  public boolean markAsDone() {
    if (this.isDone) return false;
    this.isDone = true;
    return true;
  }

  /**
   * Marks task as undone if task is currently done.
   * @return boolean representing success of operation.
   */
  public boolean markAsUndone() {
    if (!this.isDone) return false;
    this.isDone = false;
    return true;
  }

  /**
   * Getter for description of task.
   * @return description of task.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Get status icon of task.
   * @return Character representing the status of the task (whether it is done or not)
   */
  public String getStatusIcon() {
    return (this.isDone ? "X" : " ");
  }

  /**
   * Get string representation of task.
   * @return String representation of task.
   */
  @Override
  public String toString() {
    return String.format("[%s] %s", this.getStatusIcon(), this.description);
  }
}
