public class Deadline extends Task {
  private String by;

  public Deadline(String description, Boolean isDone, String by) {
    super(description, isDone);
    this.by = by;
  }

  public Deadline(String description, String by) {
    super(description);
    this.by = by;
  }

  @Override
  public String toString() {
    return String.format("[D]%s (by: %s)", super.toString(), this.by);
  }

  @Override
  public String[] getPrintRepresentation() {
    String[] strArray = super.getPrintRepresentation();
    return new String[]{"Deadline", strArray[1], strArray[2], this.by};
  }
}
