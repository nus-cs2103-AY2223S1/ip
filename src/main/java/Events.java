public class Events extends Task {
  private String specificTime;

  public Events(String description, String specificTime) {
    super(description);
    this.specificTime = specificTime;
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + "(at: " + this.specificTime + ")";
  }
}
