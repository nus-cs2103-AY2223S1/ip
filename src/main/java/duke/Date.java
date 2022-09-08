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
     *
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Date o) {
        return getFormattedDate().compareTo(o.getFormattedDate());
    }
}
