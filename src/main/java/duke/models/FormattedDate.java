package duke.models;

import duke.exceptions.DukeException;
import duke.utils.Interval;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class FormattedDate {
    protected LocalDate date;
    DateTimeFormatter parserOptionalFormats = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE)
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