package components;

/**
 * Represents deadline tasks.
 */
public class Todo extends Task {
  public Todo(String description) {
    super(description);
  }

  /**
   * Returns string representation of Deadline.
   *
   * @return string representation.
   */
  @Override
  public String toString() {
    return "[T]" + super.toString();
  }

}
