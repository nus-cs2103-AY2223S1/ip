package duke.task;

import duke.parser.DukeDateTimeFormatter;
import duke.exception.DukeException;

/**
 * Encapsulates an event task stored by Apollo.
 *
 * @author Kartikeya
 */
public class Event extends DukeTask {
  // Time of event
  private final String at;
  private final String description;
  private String formattedAt;

  public Event(String description, String at) throws DukeException {
    super(description);
    this.description = description;
    this.at = at;
    formatTime();
  }

  /**
   * Formats time to the specified format of `MMM d, yyyy | h:mma`
   * @throws DukeException if the input is erroneous
   */
  private void formatTime() throws DukeException {
    this.formattedAt = DukeDateTimeFormatter.format(at);
  }

  /**
   * {@inheritDoc}
   */
  public String getStorageString() {
    return "E >> " + (this.isDone() ? "1" : "0") + " >> "
        + this.description + " >> " + this.at;
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (at: " + formattedAt + ")";
  }
}
