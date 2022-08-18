public class Event extends Task {
  private String by;

  public Event(String description, String by) {
    super(description);
    this.by = by;
  }

  @Override
  public String toString() {
    return String.format("[E]%s (at: %s)", super.toString(), this.by);
  }
}
