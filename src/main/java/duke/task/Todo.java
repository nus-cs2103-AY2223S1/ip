package duke.task;

public class Todo extends Task {
  public Todo(String description, boolean isDone) {
    super(description, isDone);
  }

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
