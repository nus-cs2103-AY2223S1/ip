public class Task {
  private String description;
  private boolean isDone = false;

  public Task(String description) {
    this.description = description;
  }

  public void markAsDone() {
    this.isDone = true;
  }

  public void markAsNotDone() {
    this.isDone = false;
  }

  @Override
  public String toString() {
    String checkbox = this.isDone ? "[X]" : "[ ]";
    return checkbox + " " + this.description;
  }
}
