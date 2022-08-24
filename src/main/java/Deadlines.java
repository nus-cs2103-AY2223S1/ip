import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
  private String dateTime;
  private LocalDate formattedDateTime;
  private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd uuuu");

  public Deadlines(String description, String dateTime, LocalDate formattedDateTime) {
    super(description);
    this.dateTime= dateTime;
    this.formattedDateTime = formattedDateTime;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + this.formattedDateTime.format(formatter) + ")";
  }

}
