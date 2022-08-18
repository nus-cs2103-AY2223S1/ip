/**
 * Encapsulates a deadline task stored by Apollo.
 *
 * @author Kartikeya
 */
public class Deadline extends DukeTask {
  // Time of deadline
  private final String by;

  public Deadline(String description, String by) {
    super(description);
    this.by = by;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + by + ")";
  }
}
