package tasks;

/**
 * Tasks without any date/time attached to it
 */
public class ToDo extends Task {

  /**
   * Constructor for Todo
   * @param description Description of Todo
   */
  public ToDo(String description) {
    super(description);
  }

  /**
   * Returns string representation of Todo
   *
   * @return String representation of Todo
   */
  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}
