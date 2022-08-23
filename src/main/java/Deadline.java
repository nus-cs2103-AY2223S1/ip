public class Deadline extends Task {
  private final String dueDate;

  public Deadline(String description, String dueDate) {
    super(description);
    this.dueDate = dueDate;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public String toFileFormat() {
    int isDone = this.getStatusIcon() == "X" ? 1 : 0;
    return String.format("D|%d|%s|%s", isDone, this.getDescription(), this.dueDate);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return String.format("[D]%s (by: %s)", super.toString(), this.dueDate);
  }
}
