/**
 * Encapsulates an event task stored by Apollo.
 *
 * @author Kartikeya
 */
public class Event extends DukeTask {
  // Time of event
  private final String at;

  public Event(String description, String at) {
    super(description);
    this.at = at;
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (at: " + at + ")";
  }
}
