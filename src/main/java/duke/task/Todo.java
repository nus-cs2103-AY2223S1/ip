package duke.task;

public class Todo extends Task {

  public static final String SYMBOL = "T";

  public Todo(String title, boolean status) {
    super(title, status);
  }

  @Override
  public String encode() {
    return SYMBOL + " | " + (this.status ? "1" : "0") + " | " + this.title;
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}
