import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final String inputFormat = "yyyy-MM-dd HHmm"; // 2019-10-15 1800
    private final String outputFormat = "MMM dd yyyy"; // Oct 15 2019
    private final LocalDateTime endDate;

    public Deadline(String description, String endDate) throws SkylarkException {
        super(description);
        try {
            this.endDate = LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern(inputFormat));
        } catch (DateTimeParseException dateTimeParseException) {
            throw new SkylarkException("Cannot parse date");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.endDate.format(DateTimeFormatter.ofPattern(outputFormat)) + ")";
    }
}