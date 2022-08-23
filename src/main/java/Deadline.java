public class Deadline extends Task {
  private DateTime dateTime;

  public Deadline(String description, String by) {
    super(description);
    this.dateTime = new DateTime(by);
  }

  @Override
  public String toString() {
    return String.format("[D]%s (by: %s)", super.toString(), this.dateTime.toString());
  }
}
