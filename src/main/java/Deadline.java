public class Deadline extends Task {
  private String deadline;

  public Deadline(String description, String deadline) {
    this.description = description;
    this.deadline = deadline;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + this.deadline + ")";
  }
}
