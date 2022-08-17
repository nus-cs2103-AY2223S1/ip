package tasks;

public abstract class Task {

  /** isDone keeps track of whether the task is marked */
  private boolean isDone;
  /** Description of task */
  private String description;

  /**
   * Constructor of task, and initialise it as unmarked
   * @param txt Description of task
   */
  public Task(String txt) {
    this.isDone = false;
    this.description = txt;
  }

  /**
   * Based on isDone, return the correct status Icon
   * @return String representation of status Icon
   */
  public String getStatusIcon() {
    return (isDone ? "[X]" : "[ ]"); // mark done task with X
  }

  /**
   * Mark task as done
   */
  public void markAsDone() {
    this.isDone = true;
  }

  /**
   * Mark task as undone
   */
  public void markUndone() {
    this.isDone = false;
  }

  /**
   * String representaion of task
   *
   * @return String representation of task
   */
  @Override
  public String toString() {
    return this.getStatusIcon() + " " + this.description;
  }
}
