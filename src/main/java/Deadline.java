import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
  private final LocalDate dueDate;

  public Deadline(String description, String dueDateString) throws EkudException {
    super(description);
    this.dueDate = this.getDateFromString(dueDateString);
  }

  private LocalDate getDateFromString (String dateString) throws EkudException {
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
      LocalDate date = LocalDate.parse(dateString, formatter);
      return date;
    } catch (DateTimeParseException exception) {
      throw new EkudException("Invalid date format. Please input date in format yyyy-mm-dd");
    }
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public String toFileFormat() {
    int isDone = this.getStatusIcon() == "X" ? 1 : 0;
    return String.format("D|%d|%s|%s", isDone, this.getDescription(), this.dueDate.toString());
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return String.format("[D]%s (by: %s)", super.toString(), this.dueDate.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")));
  }
}
