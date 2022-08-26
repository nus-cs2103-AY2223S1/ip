package duke.task;

/**
 * Stores the description of a task and whether it is done.
 */
public abstract class Task {
  private static final String DONE = "X";
  private static final String NOT_DONE = " ";
  private String description;
  private boolean isDone;

  /**
   * Initialise the task object.
   *
   * @param description
   */
  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  /**
   * Initialise the task object.
   *
   * @param description
   * @param isDone
   */
  public Task(String description, boolean isDone) {
    this.description = description;
    this.isDone = isDone;
  }

  /**
   * Method that returns the boolean isDone of the task.
   *
   * @return isDone
   */
  public boolean isDone() {
    return isDone;
  }

  /**
   * Method that returns the description of the task.
   *
   * @return description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Method to obtain the status icon.
   *
   * @return statusIcon
   */
  public String getStatusIcon() {
    return (isDone ? DONE : NOT_DONE);
  }

  /**
   * Method to mark task as done.
   */
  public void markAsDone() {
    this.isDone = true;
  }

  /**
   * Method to mark task not done.
   */
  public void markNotDone() {
    this.isDone = false;
  }

  /**
   * Method to return the task as a string.
   *
   * @return string
   */
  public String toString() {
    return String.format("[%s] %s", this.getStatusIcon(), description);
  }

  /**
   * Method to return the task for saving and loading.
   *
   * @return strArray
   */
  public String[] getPrintRepresentation() {
    return new String[] { "Task", description, String.valueOf(isDone) };
  }
}
