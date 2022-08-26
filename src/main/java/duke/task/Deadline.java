package duke.task;

import duke.parser.DukeDateTimeFormatter;
import duke.exception.DukeException;

/**
 * Encapsulates a deadline task stored by Apollo.
 *
 * @author Kartikeya
 */
public class Deadline extends DukeTask {
  // Time of deadline
  private final String by;
  private final String description;
  private String formattedBy;

  public Deadline(String description, String by) throws DukeException {
    super(description);
    this.description = description;
    this.by = by;
    formatTime();
  }

  private void formatTime() throws DukeException {
    this.formattedBy = DukeDateTimeFormatter.format(by);
  }

  public String getStorageString() {
    return "D >> " + (this.isDone() ? "1" : "0") + " >> "
        + this.description + " >> " + this.by;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + formattedBy + ")";
  }
}
