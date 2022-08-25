public class Event extends Task {
  /** Time interval of Event */
  private String timeInterval;

  /**
   * Constructor to create a new Event
   * 
   * @param description  description of Event
   * @param timeInterval time interval of Event
   */
  public Event(boolean isDone, String description, String timeInterval) {
    super(isDone, description);
    this.timeInterval = timeInterval;
  }

  public static Event createEvent(String description, String timeInterval) {
    return new Event(false, description, timeInterval);
  }

  public static Event loadEvent(boolean isDone, String description, String timeInterval) {
    return new Event(isDone, description, timeInterval);
  }

  /**
   * Returns string representation of Event
   * 
   * @return string representation of Event
   */
  @Override
  public String toString() {
    return "[E]" + super.toString() + " (at: " + this.timeInterval + ")";
  }
}
