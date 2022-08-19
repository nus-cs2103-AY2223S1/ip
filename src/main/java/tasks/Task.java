package tasks;

public class Task {

  public enum Type {
    TODO,
    EVENT,
    DEADLINE;
  }

  Type taskType;

  protected String taskDescription;
  protected String dateDescription;
  protected boolean isDone;

  public Task(String taskDescription) {
    this.taskDescription = taskDescription;
    this.isDone = false;
    this.taskType = Type.TODO;
  }

  public Task(String taskDescription, String dateDescription, Type taskType) {
    this.taskDescription = taskDescription;
    this.dateDescription = dateDescription;
    this.taskType = taskType;
  }

  public String getStatusIcon() {
    return ("[" + (isDone ? "X" : " ") + "]");
  }


  /*
   * Updates the status of the task. If task status
   * is already what it's supposed to be changed to,
   * then no change occurs and this functions returns a reminder.
   *
   * @param changeTo The status that the task should be changed to.
   * If true, then isDone is changed to true. If false, then isDone is changed
   * to false.
   *
   * @return Returns a string that describes the change made/not made.
   */
  public String updateStatus(boolean changeTo) {
    if (isDone) {
      if (changeTo) {
        return ("Honeypie, this task has already been marked as done!");
      } else {
        isDone = !isDone;
        return ("This task has been successfully marked as not done yet!");
      }
    } else {
      if (changeTo) {
        isDone = !isDone;
        return ("This task has been successfully marked as done!");
      } else {
        return ("Honeypie, this task has already been marked as not done yet!");
      }
    }
  }

  @Override
  public String toString() {
    switch (taskType) {
      case TODO: 
        return "[T]" + getStatusIcon() + " " + taskDescription;
      case DEADLINE:
        return "[D]" + getStatusIcon() + " " + taskDescription + " (by: " + dateDescription + ")";
      case EVENT:
        return "[E]" + getStatusIcon() + " " + taskDescription + " (at: " + dateDescription + ")";
      default:
        return "";
    }
  }
}
