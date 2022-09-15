package duke.tasks;

import duke.exceptions.DukeException;
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
    private String timeToString() throws DukeException {
        assert(time > 0);
        int hours = calculateHourFromTime(time);
        int minutes = calculateMinutesFromTime(time);
        String hourString = getHours(hours);
        String minuteString = getMinutes(minutes);
        String timeOfDay = getTimeOfDay(hours);
        String result = concatenateStrings(hourString, minuteString, timeOfDay);
        return result;
    }

    /**
     * Calculates the number of hours given the time
     *
     * @param time the Time instance of the deadline or event
     * @return the number of hours in the time
     */
    private int calculateHourFromTime(int time) {
        return (int) Math.floor(time / 100);
    }

    /**
     * Calculates the number of minutes given the time
     *
     * @param time the Time instance of the deadline or event
     * @return the number of minutes in the time
     */
    private int calculateMinutesFromTime(int time) {
        return time % 100;
    }

    /**
     * Gets the string representation of the number of hours
     *
     * @param hours The number of hours in the date format
     * @return the string representation of the number of hours
     */
    private String getHours(int hours) {
        if (hours == 0) {
            return "12.";
        } else if (hours > 12) {
            return (hours - 12) + ".";
        } else {
            return hours + ".";
        }
    }

    /**
     * Gets the time of the day, whether it is a.m. or p.m.
     *
     * @param hours The hour value of the time
     * @return "a.m." if the hours is less than 12 p.m., "p.m." otherwise
     */
    private String getTimeOfDay(int hours) {
        assert(hours > 0);
        if (hours > 12) {
            hours -= 12;
            return "p.m.";
        } else if (hours == 12) {
            return "a.m.";
        } else {
            return "a.m.";
        }
    }

    /**
     * Gets the string representation of the number of minutes
     *
     * @param minutes The minutes in time
     * @return a string representation of minutes
     */
    private String getMinutes(int minutes) {
        if (minutes == 0) {
            return "00";
        } else if (minutes < 10) {
            return "0" + minutes;
        } else {
            return Integer.toString(minutes);
        }
    }

    /**
     * Concatenates a variable number of strings together
     *
     * @param strings a variable number of strings to concatenate
     * @return a string that is resulted from concatenating the multiple strings
     */
    private String concatenateStrings(String... strings) {
        StringBuilder builder = new StringBuilder();
        for (String s : strings) {
            builder.append(s);
        }
        return builder.toString();
    }

    /**
     * Returns the string representation of the Date object
     *
     * @return the string representation of the Date object
     */
    @Override
    public String toString() {
        try {
            return dateToString() + " " + timeToString();
        } catch (DukeException e) {
            e.printStackTrace();
            return "";
        }
    }
}
