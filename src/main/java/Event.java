public class Event extends Task {
  /** Time interval of Event */
  private String timeInterval;

  /**
   * Constructor to create a new Event
   * 
   * @param description  description of Event
   * @param timeInterval time interval of Event
   */
  public Event(String description, String timeInterval) {
    this.description = description;
    this.timeInterval = timeInterval;
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
