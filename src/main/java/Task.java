/**
 * Stores the description of a task and whether it is done.
 */
public class Task {
  private static final String DONE = "X";
  private static final String NOT_DONE = " ";
  private String description;
  private boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
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
}
