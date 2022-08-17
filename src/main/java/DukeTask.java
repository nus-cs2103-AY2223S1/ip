public class DukeTask {
  private String description;
  private boolean isDone;

  public DukeTask(String description) {
    this.description = description;
    this.isDone = false;
  }

  public String getStatusIcon() {
    return (isDone ? "X" : " "); // mark done task with X
  }

  public void markAsDone() {
    isDone = true;
  }

  public void markAsNotDone() {
    isDone = false;
  }

  @Override
  public String toString() {
    return "[" + getStatusIcon() + "] " + description;
  }
}
