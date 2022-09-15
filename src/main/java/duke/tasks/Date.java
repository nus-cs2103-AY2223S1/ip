package duke.tasks;

import duke.exceptions.DukeInvalidDateException;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

/**
 * Encapsulates a Date object for use in the Deadline and
 * Event classes
 */
public class Date {

    protected LocalDate date;
    protected int time;

    /**
     * Initialises the Date object
     *
     * @param str A string containing the date and time in a specific format
     * @throws DukeInvalidDateException if the date and time does not follow the
     *     correct format
     */
    public Date(String str) throws DukeInvalidDateException {
        assert(str != "");
        String[] splittedValues = str.split("\\s+");
        this.date = parse(splittedValues[0]);
        if (splittedValues.length > 1) {
            this.time = Integer.valueOf(splittedValues[1]);
        }
    }

    /**
     * Parses the date string to the LocalDate object
     *
     * @param str A string containing the date in a specific format
     * @return A LocalDate containing the date representation of str
     * @throws DukeInvalidDateException if the string does not follow the correct
     *     date format
     */
    private LocalDate parse(String str) throws DukeInvalidDateException {
        assert(str != "");
        LocalDate date;
        try {
            date = LocalDate.parse(str);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateException();
        }
        return date;
    }

    /**
     * Returns the string representation of the date field
     *
     * @return the string representation of the date field in the "MMM d YYYY" format
     */
    private String dateToString() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns the string representation of the time field
     *
     * @return the string representation of the time field in the "a.m/p.m" format
     */
    private String timeToString() {
        assert(time > 0);
        int hours = (int) Math.floor(time / 100);
        int minutes = time % 100;
        String timeOfDay = "a.m.";
        String result = "";

        if (hours > 12) {
            hours -= 12;
            timeOfDay = "p.m.";
        } else if (hours == 12) {
            timeOfDay = "a.m.";
        }

        if (hours == 0) {
            result += "12.";
        } else {
            result += hours + ".";
        }

        if (minutes == 0) {
            result += "00";
        } else if (minutes < 10) {
            result += "0" + minutes;
        } else {
            result += minutes;
        }

        result += timeOfDay;
        return result;
    }

    /**
     * Returns the string representation of the Date object
     *
     * @return the string representation of the Date object
     */
    @Override
    public String toString() {
        return dateToString() + " " + timeToString();
    }
}
