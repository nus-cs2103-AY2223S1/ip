public class Todo extends Task {
  public Todo(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}
