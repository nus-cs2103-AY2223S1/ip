public class Todo extends Task {
  public Todo(String title, boolean done) {
    super(title, done);
  }

  @Override
  public String toString() {
    String checkbox = this.getDone() ? "[T][X]" : "[T][ ]";
    return checkbox + " " + super.getTitle();
  }
}
