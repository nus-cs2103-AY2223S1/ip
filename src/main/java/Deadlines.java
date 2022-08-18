public class Deadlines extends Task {
  private String dateTime;

  public Deadlines(String description, String dateTime) {
    super(description);
    this.dateTime = dateTime;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + this.dateTime + ")";
  }

}
