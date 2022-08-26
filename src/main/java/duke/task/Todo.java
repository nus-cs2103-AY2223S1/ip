package duke.task;

/**
 * A representation of Todo
 */
public class Todo extends Task {

  /**
   * Initialises Todo
   *
   * @param description
   * @param isDone
   */
  public Todo(String description, boolean isDone) {
    super(description, isDone);
  }

  /**
   * Initialises Todo
   *
   * @param description
   */
  public Todo(String description) {
    super(description);
  }

  @Override
  public String toString() {
    return String.format("[T]%s", super.toString());
  }

  @Override
  public String[] getPrintRepresentation() {
    String[] strArray = super.getPrintRepresentation();
    return new String[] { "Todo", strArray[1], strArray[2] };
  }
}
