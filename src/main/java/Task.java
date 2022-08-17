public class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
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
    return (getStatusIcon() + " " + description);
  }
}
