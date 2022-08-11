public class Task {
  private final String description;
  private boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public boolean markAsDone() {
    if (this.isDone) return false;
    this.isDone = true;
    return true;
  }

  public boolean markAsUndone() {
    if (!this.isDone) return false;
    this.isDone = false;
    return true;
  }

  public String getDescription() {
    return this.description;
  }

  public String getStatusIcon() {
    return (this.isDone ? "X" : " ");
  }

  @Override
  public String toString() {
    return String.format("[%s] %s", this.getStatusIcon(), this.description);
  }
}
