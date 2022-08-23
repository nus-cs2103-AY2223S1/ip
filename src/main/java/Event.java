public class Event extends Task {
  private DateTime dateTime;

  public Event(String description, String by) {
    super(description);
    this.dateTime = new DateTime(by);
  }

  @Override
  public String toString() {
    return String.format("[E]%s (at: %s)", super.toString(), this.dateTime.toString());
  }
}
