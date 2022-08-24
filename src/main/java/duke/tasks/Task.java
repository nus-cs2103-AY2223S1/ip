package duke.tasks;

public abstract class Task {

  /** isDone keeps track of whether the task is marked */
  private boolean isDone;
  /** Description of task */
  private String description;

  /**
   * Constructor of Task, initializes as unmarked
   * 
   * @param txt Description of task
   */
  public Task(String txt) {
    this.isDone = false;
    this.description = txt;
  }

  /**
   * Constructor of Task that initializes isDone state of task
   * 
   * @param txt task description
   * @param isDone whether task has been completed
   */
  public Task(String txt, boolean isDone) {
    this.isDone = isDone;
    this.description = txt;
  }

  /**
   * Return the status icon of Task
   * 
   * @return String representation of status Icon
   */
  public String getStatusIcon() {
    return (isDone ? "[X]" : "[ ]"); // mark done task with X
  }

  /**
   * Marks Task as done
   */
  public void markAsDone() {
    this.isDone = true;
  }

  /**
   * Marks Task as undone
   */
  public void markUndone() {
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
