import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
  /** Datetime of deadline */
  private LocalDateTime deadline;

  /**
   * Constructor to create a new Deadline
   * 
   * @param description description of Deadline
   * @param deadline    deadline of Deadline
   */
  public Deadline(String description, String deadline) {
    super(description);
    String inputPattern = "yyyy-dd-MM HH:mm";
    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputPattern);
    this.deadline = LocalDateTime.parse(deadline, inputFormatter);
  }

  public Deadline(boolean isDone, String description, String deadline) {
    super(isDone, description);
    String inputPattern = "yyyy-dd-MM HH:mm";
    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputPattern);
    this.deadline = LocalDateTime.parse(deadline, inputFormatter);
  }

  @Override
  public String toFileString() {
    return "deadline // " + super.toFileString() + " // " + deadline;
  }

  /**
   * Returns string representation of Deadline
   * 
   * @return string representation of Deadline
   */
  @Override
  public String toString() {
    String outputPattern = "dd-MM-yyyy HH:mm";
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputPattern);
    return "[D]" + super.toString() + " (by: " + outputFormatter.format(deadline) + ")";
  }
}
