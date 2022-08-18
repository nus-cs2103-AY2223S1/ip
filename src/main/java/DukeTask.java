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

  public String markAsDone() {
    isDone = true;
    return "\033[0;32mGood job!\033[0m This task has been completed:\n"
        + this;
  }

  public String markAsNotDone() {
    isDone = false;
    return "\033[0;33mWhoops!\033[0m This task is now yet to be completed:\n"
        + this;
  }

  @Override
  public String toString() {
    return "[" + getStatusIcon() + "] " + description;
  }
}
