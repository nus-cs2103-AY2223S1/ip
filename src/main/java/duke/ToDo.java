package duke;

/**
 * Represents a todo task with its description
 * and completion status
 *
 * @author  Gerald Teo Jin Wei
 * @version 0.1
 * @since   2022-08-28
 */
public class ToDo extends Task{

  public ToDo(String description) {
    super(description);
  }

  public ToDo(String description, boolean isDone) {
    super(description,isDone);
  }
  /**
   * This method returns the task's type, completion status and description
   * @return String This returns the string of the todo task in the specified format
   */
  public String toString() {
    return "[T][" + this.getStatusIcon() + "] " + this.description;
  }

}
