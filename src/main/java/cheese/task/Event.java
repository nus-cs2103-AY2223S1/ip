package cheese.task;

import java.time.LocalDateTime;

import cheese.utils.DateTimeUtils;

public class Event extends Task {
  /** Time interval of Event */
  private LocalDateTime timeInterval;

  public Event(String description, String timeInterval) {
    super(description);
    this.timeInterval = DateTimeUtils.parseString(timeInterval);
  }

  /**
   * Constructor to create a new Event
   * 
   * @param description  description of Event
   * @param timeInterval time interval of Event
   */
  public Event(boolean isDone, String description, String timeInterval) {
    super(isDone, description);
    this.timeInterval = DateTimeUtils.parseString(timeInterval);
  }

  @Override
  public String toFileString() {
    String formattedTimeInterval = DateTimeUtils.parseLocalDateTimeToInput(timeInterval);
    return "event // " + super.toFileString() + " // " + formattedTimeInterval;
  }

  /**
   * Returns string representation of Event
   * 
   * @return string representation of Event
   */
  @Override
  public String toString() {
    String formattedTimeInterval = DateTimeUtils.parseLocalDateTimeToOutput(timeInterval);
    return "[E]" + super.toString() + " (at: " + formattedTimeInterval + ")";
  }
}
