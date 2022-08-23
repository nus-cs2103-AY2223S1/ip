/**
 * Encapsulates an event task stored by Apollo.
 *
 * @author Kartikeya
 */
public class Event extends DukeTask {
  // Time of event
  private final String at;
  private final String description;

  public Event(String description, String at) {
    super(description);
    this.description = description;
    this.at = at;
  }

  public String getStorageString() {
    return "E >> " + (this.isDone() ? "1" : "0") + " >> "
        + this.description + " >> " + this.at;
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (at: " + at + ")";
  }
}
