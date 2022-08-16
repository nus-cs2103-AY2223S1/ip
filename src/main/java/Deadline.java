public class Deadline extends Task {
  private String by;

  public Deadline(String description, boolean done, String by) {
    super(description, done);
    this.by = by;
  }

  @Override
  public String toString() {
    String checkbox = this.getDone() ? "[D][X]" : "[D][ ]";
    String dateFormatted = "(by: " + by + ")";
    return checkbox + " " + super.getDescription() + " " + dateFormatted;
  }
}
