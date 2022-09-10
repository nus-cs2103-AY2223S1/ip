package duke.task;

import duke.exception.WrongArgumentException;

import java.time.DayOfWeek;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

public class Recurring extends Task {
    private enum DAYS { mon, tue, wed, thu, fri, sat, sun }
    private String startDate;
    private LocalDateTime now;
    private int timesRemaining;
    private ArrayList<LocalDateTime> dates = new ArrayList<>();


    public Recurring(String desc, String date, int numberOfTimes) throws WrongArgumentException {
        super(desc);
        this.now = LocalDateTime.now();
        this.startDate = date;
        this.timesRemaining = numberOfTimes;
        calculatePeriod(date, numberOfTimes);
    }

    private void calculatePeriod(String input, int times) throws WrongArgumentException {
        LocalDateTime dateTime;
        LocalTime time = LocalTime.NOON;
        LocalDate date;
        if (input.contains("/at")) {
            String[] arr = input.split("/at");
            time = LocalTime.parse(arr[1]);
            input = arr[0];
        }

        try {
             date = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yy")).withYear(this.now.getYear());
             dateTime = LocalDateTime.of(date, time);
             yearly(dateTime, times);
        } catch (DateTimeException e) {
            try {
                int n = Integer.parseInt(input);
                date = LocalDate.of(this.now.getYear(), this.now.getMonth(), n);
                dateTime = LocalDateTime.of(date, time);
                monthly(dateTime, times);
            } catch (NumberFormatException f) {
                try {
                    LocalDate now = LocalDate.now();
                    date = now.with(TemporalAdjusters.next(DayOfWeek.from(DateTimeFormatter.ofPattern("E").parse(input))));
                    dateTime = LocalDateTime.of(date, time);
                    weekly(dateTime, times);
                } catch (DateTimeException g) {
                    try {
                        time = LocalTime.parse(input, Task.INPUT_TIME_FORMAT);
                        dateTime = LocalDateTime.of(this.now.toLocalDate(), time);
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
        for (int i = 0; i < times; i++) {
            this.dates.add(first.plusYears(i));
        }
    }

    private void monthly(LocalDateTime dateTime, int times) {
        LocalDateTime first = dateTime;
        if (this.now.isAfter(dateTime)) {
            first = dateTime.plusMonths(1);
        }
        for (int i = 0; i < times; i++) {
            this.dates.add(first.plusMonths(i));
        }
    }

    private void weekly(LocalDateTime dateTime, int times) {
        LocalDateTime first = dateTime;
        if (this.now.isAfter(dateTime)) {
            first = dateTime.plusWeeks(1);
        }
        for (int i = 0; i < times; i++) {
            this.dates.add(first.plusWeeks(i));
        }
    }

    private void daily(LocalDateTime dateTime, int times) {
        LocalDateTime first = dateTime;
        if (this.now.isAfter(dateTime)) {
            first = dateTime.plusDays(1);
        }
        for (int i = 0; i < times; i++) {
            this.dates.add(first.plusDays(i));
        }
    }

    

    @Override
    public String format() {
        return "recurring " + this.description + " /every " + this.startDate + " *" + this.timesRemaining
                + "|" + this.getStatusIcon();
    }

    @Override
    public String toString() {
        return "[R]" + super.toString() + " (next: " + this.startDate + "; remaining: " + this.timesRemaining + ")";
    }
}
