package tasks;

public class Task {

  private boolean isDone;
  private String description;

  public Task(String txt) {
    this.isDone = false;
    this.description = txt;
  }

  public String getStatusIcon() {
    return (isDone ? "[X]" : "[ ]"); // mark done task with X
  }

  public void markAsDone() {
    this.isDone = true;
  }

  public void markUndone() {
    this.isDone = false;
  }

  //   public String getDescription() {
  //     return this.description;
  //   }

  @Override
  public String toString() {
    return this.getStatusIcon() + " " + this.description;
  }
}
