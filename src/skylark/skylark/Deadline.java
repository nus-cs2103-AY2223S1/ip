package skylark;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final char SYMBOL = 'D';

    private final String inputFormat = "yyyy-MM-dd HHmm"; // 2019-10-15 1800
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
        // Oct 15 2019
        String outputFormat = "MMM dd yyyy";
        return String.format("[%c] %s (by: %s)",
                SYMBOL, super.toString(), this.endDate.format(DateTimeFormatter.ofPattern(outputFormat)));
    }

    @Override
    public String toStringFile() {
        return String.format("%c | %d | %s | %s", SYMBOL,
                super.getStatusIcon().equals("X") ? 1 : 0, super.getDescription(),
                this.endDate.format(DateTimeFormatter.ofPattern(inputFormat)));
    }
}