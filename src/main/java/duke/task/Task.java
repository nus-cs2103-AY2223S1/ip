package duke.task;

/**
 * Stores the description of a task and whether it is done.
 */
public abstract class Task {
  private static final String DONE = "X";
  private static final String NOT_DONE = " ";
  private String description;
  private boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public Task(String description, boolean isDone) {
    this.description = description;
    this.isDone = isDone;
  }

  public boolean isDone() {
    return isDone;
  }

  public String getDescription() {
    return description;
  }

  public String getStatusIcon() {
    return (isDone ? DONE : NOT_DONE);
  }

  public void markAsDone() {
    this.isDone = true;
  }

  public void markNotDone() {
    this.isDone = false;
  }

  public String toString() {
    return String.format("[%s] %s", this.getStatusIcon(), description);
  }

  public String[] getPrintRepresentation() {
    return new String[]{"Task", description, String.valueOf(isDone)};
  }
}
