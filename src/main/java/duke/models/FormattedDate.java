package duke.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import duke.utils.Interval;

/**
 * A standardised date class.
 */
public class FormattedDate {
    protected LocalDate date;
    protected DateTimeFormatter parserOptionalFormats = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE)
            .appendOptional(DateTimeFormatter.ofPattern("MMM dd yyyy"))
            .toFormatter();

    public FormattedDate(String str) {
        this.date = LocalDate.parse(str, parserOptionalFormats);
    }

    public FormattedDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Creates a new FormattedDate that is the specified interval
     * after the given formattedDate.
     *
     * @param formattedDate Reference datae to generate next FormattedDate.
     * @param interval Interval between given date and new FormattedDate.
     * @return FormattedDate that is a specified interval after given date.
     */
    public static FormattedDate addIntervalToDate(FormattedDate formattedDate, Interval interval) {
        LocalDate newDate = formattedDate.getDate();
        System.out.println(newDate);
        switch (interval) {
        case Day:
            newDate = newDate.plusDays(1);
            break;
        case Week:
            newDate = newDate.plusWeeks(1);
            break;
        case Month:
            newDate = newDate.plusMonths(1);
            break;
        default:
            break;
        }
        return new FormattedDate(newDate);
    }
}
