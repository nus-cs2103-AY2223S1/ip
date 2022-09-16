package duke.task;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

import duke.exception.WrongArgumentException;

/**
 * Tasks that are recurring with yearly, monthly, weekly or daily frequency.
 */
public class Recurring extends Task {
    private static final DateTimeFormatter OUTPUT = DateTimeFormatter.ofPattern("dd MMM yy h:mma");
    private enum FREQUENCY_PERIOD { yearly, monthly, weekly, daily }
    private FREQUENCY_PERIOD period;
    private final String dateKeyedIn;
    private LocalDateTime startDate;
    private final LocalDateTime now;
    private final int originalTimes;
    private int timesRemaining;
    private final ArrayList<LocalDateTime> dates = new ArrayList<>();


    /**
     * Constructor for new Recurring task.
     * @param desc Description of task.
     * @param date Start date of the recurring task in dd/MM/yy format.
     * @param numberOfTimes How many times is the task recurring for.
     * @throws WrongArgumentException Thrown when input date/time is in the wrong format.
     */
    public Recurring(String desc, String date, int numberOfTimes) throws WrongArgumentException {
        super(desc);
        this.now = LocalDateTime.now();
        this.dateKeyedIn = date;
        this.originalTimes = numberOfTimes;
        this.timesRemaining = numberOfTimes;
        calculatePeriod(date, numberOfTimes);
    }

    private void calculatePeriod(String input, int times) throws WrongArgumentException {
        LocalDateTime dateTime;
        LocalTime time = LocalTime.NOON;
        LocalDate date;
        if (input.contains("/at")) {
            String[] arr = input.split(" /at ");
            time = LocalTime.parse(arr[1], DateTimeFormatter.ofPattern("HHmm"));
            input = arr[0];
        }

        try {
            // recurring yearly
            date = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yy")).withYear(this.now.getYear());
            dateTime = LocalDateTime.of(date, time);
            this.period = FREQUENCY_PERIOD.yearly;
            yearly(dateTime, times);
        } catch (DateTimeException e) {
            try {
                // recurring monthly
                int n = Integer.parseInt(input);
                if (n > 31) {
                    throw new NumberFormatException();
                }
                date = LocalDate.of(this.now.getYear(), this.now.getMonth(), n);
                dateTime = LocalDateTime.of(date, time);
                this.period = FREQUENCY_PERIOD.monthly;
                monthly(dateTime, times);
            } catch (NumberFormatException f) {
                try {
                    // recurring weekly
                    LocalDate now = LocalDate.now();
                    DateTimeFormatter day = DateTimeFormatter.ofPattern("E");
                    date = now.with(TemporalAdjusters.next(DayOfWeek.from(day.parse(input))));
                    dateTime = LocalDateTime.of(date, time);
                    this.period = FREQUENCY_PERIOD.weekly;
                    weekly(dateTime, times);
                } catch (DateTimeException g) {
                    try {
                        // recurring daily
                        time = LocalTime.parse(input, Task.INPUT_TIME_FORMAT);
                        dateTime = LocalDateTime.of(this.now.toLocalDate(), time);
                        this.period = FREQUENCY_PERIOD.daily;
                        daily(dateTime, times);
                    } catch (DateTimeException h) {
                        throw new WrongArgumentException(input, f);
                    }
                }
            }
        }
    }

    private void yearly(LocalDateTime dateTime, int times) {
        LocalDateTime first = dateTime;
        if (this.now.isAfter(dateTime)) {
            first = dateTime.plusYears(1);
        }
        this.startDate = first;
        for (int i = 0; i < times; i++) {
            this.dates.add(first.plusYears(i));
        }
    }

    private void monthly(LocalDateTime dateTime, int times) {
        LocalDateTime first = dateTime;
        if (this.now.isAfter(dateTime)) {
            first = dateTime.plusMonths(1);
        }
        this.startDate = first;
        for (int i = 0; i < times; i++) {
            this.dates.add(first.plusMonths(i));
        }
    }

    private void weekly(LocalDateTime dateTime, int times) {
        LocalDateTime first = dateTime;
        if (this.now.isAfter(dateTime)) {
            first = dateTime.plusWeeks(1);
        }
        this.startDate = first;
        for (int i = 0; i < times; i++) {
            this.dates.add(first.plusWeeks(i));
        }
    }

    private void daily(LocalDateTime dateTime, int times) {
        LocalDateTime first = dateTime;
        if (this.now.isAfter(dateTime)) {
            first = dateTime.plusDays(1);
        }
        this.startDate = first;
        for (int i = 0; i < times; i++) {
            this.dates.add(first.plusDays(i));
        }
    }

    /**
     * Calculates number of times task occurs after current time when loaded from save file.
     * @param date Date when the first task occurred.
     */
    public void calculateRemaining(LocalDateTime date) {
        LocalDateTime now = LocalDateTime.now();
        long diff = DAYS.between(date, now);
        int alreadyHappened = 0;
        if (diff > 0) {
            switch (this.period) {
            case yearly:
                alreadyHappened = (int) Math.ceil((diff / 365.0));
                break;
            case monthly:
                alreadyHappened = (int) Math.ceil((diff / 12.0));
                break;
            case weekly:
                alreadyHappened = (int) Math.ceil((diff / 7.0));
                break;
            case daily:
                alreadyHappened = (int) diff;
                break;
            default:
                assert false;
            }
        }
        this.timesRemaining = this.originalTimes - alreadyHappened;
    }

    /**
     * Lists past/future dates where the task had/going to occur.
     * @return The string of dates.
     */
    public String showDates() {
        StringBuilder str = new StringBuilder();
        for (LocalDateTime l : dates) {
            str.append("\n");
            str.append(l.format(OUTPUT));
        }
        return str.toString();
    }

    @Override
    public String format() {
        return "recurring " + this.description + " /every " + this.dateKeyedIn + " *" + this.originalTimes
                + "|" + this.getStatusIcon() + "|" + this.startDate;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[R]" + super.toString() + " (next: ");
        str.append(dates.get(this.originalTimes - this.timesRemaining).format(OUTPUT));
        str.append("; remaining: " + this.timesRemaining + ")");
        return str.toString();
    }
}
