public class Deadline extends Task{
  protected String by;
  public Deadline(String description, String by) {
    super(description);
    this.by = by;
  }

  /**
   * Returns the description of the task, with a Deadline tag
   * @return Description of the task.
   */
  @Override
  public String toString() {
    return "[D]" + super.toString() + "(by: " + by + ")";
  }
}
