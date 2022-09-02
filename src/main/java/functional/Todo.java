package functional;

import technical.SaveLine;

public class Todo extends Task {
  private static final String TODO_INFOTYPE = "todo";
  /**
   * Construct functional.Task with a fixed name.
   *
   * @param name The name of the task.
   */
  public Todo(String name) {
    super(name);
  }

  public Todo(SaveLine line) {
    super(line);
  }

  /**
   * Shows the todo name and status as a String.
   *
   * @return A String with the task name and status.
   */
  public String toString() {
    return String.format("[T]%s", super.toString());
  }

  @Override
  public SaveLine toData() {
    SaveLine ret = super.toData();
    ret.setInfoType(TODO_INFOTYPE);
    return ret;
  }

  @Override
  public boolean equals(Object rhs) {
    if (rhs instanceof Todo) {
      Todo rhsDeadline = (Todo) rhs;
      return toData().equals(rhsDeadline.toData());
    }
    return false;
  }
}
