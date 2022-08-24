/**
 * Encapsulates an event task stored by Apollo.
 *
 * @author Kartikeya
 */
public class Event extends DukeTask {
  // Time of event
  private final String at;
  private String formattedAt;

  public Event(String description, String at) {
    super(description);
    this.at = at;
    formatTime();
  }

  private void formatTime() {
    this.formattedAt = DukeDateTimeFormatter.format(at);
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (at: " + formattedAt + ")";
  }
}
