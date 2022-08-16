public class Event extends Task {
  private String at;

  public Event(String title, boolean done, String at) {
    super(title, done);
    this.at = at;
  }

  @Override
  public String toString() {
    String checkbox = this.getDone() ? "[E][X]" : "[E][ ]";
    String dateFormatted = "(at: " + at + ")";
    return checkbox + " " + super.getTitle() + " " + dateFormatted;
  }
}
