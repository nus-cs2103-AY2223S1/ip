public class Deadlines extends Task {
  private String dateTime;

  public Deadlines(String description, String dateTime) {
    super(description);
    this.dateTime = dateTime;
  }

  public String storedTaskString() {
    return "D|" + String.valueOf(this.isDone) + "|" + this.description + "|" + this.dateTime;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + this.dateTime + ")";
  }

}
