package uwu.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import uwu.exception.InvalidDateException;
import uwu.exception.UwuException;

/**
 * Represents the standardised format of a task's date and time.
 */
public class UwuDateTime {
    /** The LocalDateTime object to be returned. */
    protected LocalDateTime dateTime;

    /**
     * Constructs an UwuDateTime object.
     * The UwuDateTime standardises the representation of the date and time of tasks.
     *
     * @param input The string representation of date and time of deadline or event.
     * @throws UwuException If the date and time is invalid;
     *                      If the date and time is parsed in incorrect format.
     */
    public UwuDateTime(String input) throws UwuException {
        try {
            boolean hasNoTime = !input.contains(" ");
            if (hasNoTime) {
                input = input + " 00:00";
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.dateTime = LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("please enter a valid date, using the format: \n\t'yyyy-mm-dd HH:mm'"
                    + "\nif you do not have a time, enter the date only~");
        }
    }

    /**
     * Returns the converted DateTime.
     *
     * @return Converted LocalDateTime object.
     */
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }
}
