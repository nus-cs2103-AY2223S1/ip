public class Event extends Task {

  public static final String SYMBOL = "E";

  private final String date;

  Event(String title, boolean status, String date) {
    super(title, status);
    this.date = date;
  }

  @Override
  String saveString() {
    return (
      SYMBOL +
      " | " +
      (this.status ? "1" : "0") +
      " | " +
      this.title +
      " /at " +
      this.date
    );
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (at: " + this.date + ")";
  }
}
