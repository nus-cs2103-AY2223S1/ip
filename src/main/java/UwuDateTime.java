import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UwuDateTime {
    protected LocalDateTime dateTime;

    public UwuDateTime(String input) throws UwuException{
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            if (!input.contains(" ")) {
                input = input + " 00:00";
            }
            LocalDateTime dateTimeTemp = LocalDateTime.parse(input, formatter);
            this.dateTime = dateTimeTemp;
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Invalid date format.");
        }
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }
}
