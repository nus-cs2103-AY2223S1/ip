public class Event extends Task{
  protected String at;

  public Event(String description, String date) {
    super(description);
    this.at = date;
  }

  public String toString() {
    return "[E][" + this.getStatusIcon() + "] " + this.description + " (at: " + at + ")";
  }
}
