package date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Stores a representation of Date based on ISO-8601
 *
 * @author Bryan Lim Jing Xiang
 */
public abstract class Date {
    private LocalDate date;

    public Date(String date) throws DateTimeParseException {
        this.date = LocalDate.parse(date);
    }

    /**
     * {@inheritDoc}
     *
     * @return Date formatted in terms of "month day year", e.g Jan 1 2000
     */
    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * @return A string encoding of the stored date
     */
    public String encode() {
        return date.toString();
    }
}
