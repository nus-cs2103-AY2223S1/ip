public class Event extends Task {
  private String timeInterval;

  public Event(String description, String timeInterval) {
    this.description = description;
    this.timeInterval = timeInterval;
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (at: " + this.timeInterval + ")";
  }
}
