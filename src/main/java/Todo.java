public class Todo extends Task {
  /**
   * Constructor to create a new Todo
   * 
   * @param description description of Todo
   */
  public Todo(String description) {
    super(description);
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
