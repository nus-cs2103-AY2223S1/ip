public class Event extends Task{
  protected String at;
  public Event(String description, String at) {
    super(description);
    this.at = at;
  }

  /**
   * Returns the description of the task, with an event tag
   * @return Description of the task.
   */
  @Override
  public String toString() {
    return "[E]" + super.toString() + "(at: " + at + ")";
  }
}
