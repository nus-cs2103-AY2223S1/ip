package uwu.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import uwu.exception.InvalidDateException;
import uwu.exception.UwuException;

public class UwuDateTime {
    protected LocalDateTime dateTime;

    public UwuDateTime(String input) throws UwuException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            if (!input.contains(" ")) {
                input = input + " 00:00";
            }
            LocalDateTime dateTimeTemp = LocalDateTime.parse(input, formatter);
            this.dateTime = dateTimeTemp;
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("\tplease enter a valid date, using the format: \n\t\t'yyyy-mm-dd HH:mm'"
                    + "\n\tif you do not have a time, enter the date only~");
        }
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }
}
