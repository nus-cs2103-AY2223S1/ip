public class Deadline extends Task {

  private final String date;

  Deadline(String title, boolean status, String date) {
    super(title, status);
    this.date = date;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + this.date + ")";
  }
}
