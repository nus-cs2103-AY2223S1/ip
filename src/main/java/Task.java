public class Task {
  private final String description;
  private boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public String getDescription() {
    return this.description;
  }

  public String getStatusIcon() {
    return (this.isDone ? "X" : " ");
  }
}
