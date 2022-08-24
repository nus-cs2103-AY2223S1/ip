package duke.tasks;

public abstract class Task {

  /** isDone keeps track of whether the task is marked */
  private boolean isDone;
  /** Description of task */
  private String description;

  /**
   * Constructor of task, and initialise it as unmarked
   * 
   * @param txt Description of task
   */
  public Task(String txt) {
    this.isDone = false;
    this.description = txt;
  }

  /**
   * Overloaded constructor that intialiases isDone state of task
   * 
   * @param txt task description
   * @param isDone whether task has been completed
   */
  public Task(String txt, boolean isDone) {
    this.isDone = isDone;
    this.description = txt;
  }

  /**
   * Based on isDone, return the correct status Icon
   * 
   * @return String representation of status Icon
   */
  public String getStatusIcon() {
    return (isDone ? "[X]" : "[ ]"); // mark done task with X
  }

  /**
   * Mark task as done
   */
  public void setDone() {
    this.isDone = true;
  }

  /**
   * Mark task as undone
   */
  public void setUndone() {
    this.isDone = false;
  }

  /**
   * String representation of task
   *
   * @return String representation of task
   */
  @Override
  public String toString() {
    return this.getStatusIcon() + " " + this.description;
  }

  /**
   * String representation of task in storage
   * @return String representation of task in storage
   */
  public String toSaveString() {
    String mark = isDone ? "1" : "0";
    return "| " + mark + " | " + this.description;
  }
}
