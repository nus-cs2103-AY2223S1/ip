public class Event extends Task {

  private final String date;

  Event(String title, boolean status, String date) {
    super(title, status);
    this.date = date;
  }

  @Override
  public Event setStatus(boolean status) {
    return new Event(this.getTitle(), status, this.date);
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (at: " + this.date + ")";
  }
}
