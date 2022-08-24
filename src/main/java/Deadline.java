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

  public Deadline(String description, String by) {
    super(description);
    this.description = description;
    this.by = by;
    formatTime();
  }

  private void formatTime() {
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
