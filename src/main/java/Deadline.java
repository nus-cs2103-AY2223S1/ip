public class Deadline extends Task {

  public static final String SYMBOL = "D";

  private final String date;

  Deadline(String title, boolean status, String date) {
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
      " /by " +
      this.date
    );
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + this.date + ")";
  }
}
