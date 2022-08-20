public class Todo extends Task {

  public static final String SYMBOL = "T";

  Todo(String title, boolean status) {
    super(title, status);
  }

  @Override
  String saveString() {
    return SYMBOL + " | " + (this.status ? "1" : "0") + " | " + this.title;
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}
