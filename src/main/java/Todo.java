public class Todo extends Task{
  public Todo(String description) {
    super(description);
  }

  /**
   * Returns the description of the task, with a to-do tag
   * @return Description of the task.
   */
  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}
