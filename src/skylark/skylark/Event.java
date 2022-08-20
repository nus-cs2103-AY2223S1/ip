package skylark;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    private final char SYMBOL = 'E';

    private final String inputFormat = "yyyy-MM-dd HHmm"; // 2019-10-15 1800
    private final LocalDateTime timing;

    public Event(String description, String timing) throws SkylarkException {
        super(description);
        try {
            this.timing = LocalDateTime.parse(timing, DateTimeFormatter.ofPattern(inputFormat));
        } catch (DateTimeParseException dateTimeParseException) {
            throw new SkylarkException("Cannot parse date");
        }
    }

    @Override
    public String toString() {
        // Oct 15 2019
        String outputFormat = "MMM dd yyyy";
        return String.format("[%c] %s (at: %s)", SYMBOL,
                super.toString(), this.timing.format(DateTimeFormatter.ofPattern(outputFormat)));
    }

    @Override
    public String toStringFile() {
        return String.format("%c | %d | %s | %s", SYMBOL,
                super.getStatusIcon().equals("X") ? 1 : 0,
                super.getDescription(), this.timing.format(DateTimeFormatter.ofPattern(inputFormat)));
    }
}
