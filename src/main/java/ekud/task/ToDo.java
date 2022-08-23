package ekud.task;
public class ToDo extends Task {
  public ToDo(String description) {
    super(description);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toFileFormat() {
    int isDone = this.getStatusIcon() == "X" ? 1 : 0;
    return String.format("T|%d|%s", isDone, this.getDescription());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return String.format("[T]%s", super.toString());
  }
}
