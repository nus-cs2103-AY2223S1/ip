package cheese.task;

public class Todo extends Task {
  /**
   * Constructor to create a new Todo
   * 
   * @param description description of Todo
   */
  public Todo(String description) {
    super(description);
  }

  public Todo(boolean isDone, String description) {
    super(isDone, description);
  }

  @Override
  public String toFileString() {
    return "todo // " + super.toFileString();
  }

  /**
   * Returns string representation of Todo
   * 
   * @return string representation of Todo
   */
  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}
