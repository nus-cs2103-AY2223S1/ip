package components;

import java.time.LocalDate;

/**
 * Represents task, which a user's inputs.
 */
public class Task {
  protected String description;
  protected boolean isDone;
  protected LocalDate date;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  /**
   * get status icon of a task, with "X" representing completion and
   * a blank representing incompletion.
   *
   * @returns task task to be added into ArrayList.
   */
  public String getStatusIcon() {
    return (isDone ? "X" : " "); // mark done task with X
  }

  /**
   * Returns string representation of Task.
   *
   * @return string representation.
   */
  @Override
  public String toString() {
    return "[" + this.getStatusIcon() + "]" + " " + this.description;
  }

  /**
   * sets status of task to be completed or not.
   *
   * @param value true represents completion while false represents incompletion.
   */
  public void setStatus(boolean value) {
    this.isDone = value;
  }
}



