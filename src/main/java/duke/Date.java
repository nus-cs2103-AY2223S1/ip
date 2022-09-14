package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Provides an object for easy parsing of date formats.
 * @author Jason
 */
public class Date implements Comparable<Date> {
    protected LocalDate date;

    public Date(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public LocalDate getFormattedDate() {
        return this.date;
    }

    /**
     * Compares 2 dates with one another.
     * @param otherDate The other date to be compared.
     * @return  A negative integer, zero, or a positive integer as this date
     *          is less than, equal to, or greater than the supplied date object.
     */
    @Override
    public int compareTo(Date otherDate) {
        return getFormattedDate().compareTo(otherDate.getFormattedDate());
    }
}
