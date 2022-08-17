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
    return "Good job! This task has been completed:\n"
        + this;
  }

  public String markAsNotDone() {
    isDone = false;
    return "Whoops! This task is now yet to be completed:\n"
        + this;
  }

  @Override
  public String toString() {
    return "[" + getStatusIcon() + "] " + description;
  }
}
