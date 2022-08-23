package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Tasks that start at a specific time and ends at a specific time
 */
public class Event extends Task {

  private LocalDate at;

  /**
   * Constructor for Event
   * 
   * @param description Description of task details
   * @param by          When the task is at
   */
  public Event(String description, LocalDate at) {
    super(description);
    this.at = at;
  }

  /**
   * Returns String representation of Event
   *
   * @return String representation of Event
   */
  @Override
  public String toString() {
    return "[E]" + super.toString() + "(at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
  }
}
