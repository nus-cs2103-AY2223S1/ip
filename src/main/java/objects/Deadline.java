package objects;

import objects.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Deadline extends Task {

    private enum Calendar {
        YEAR, MONTH, DAY, TIME
    }

    private LocalDate endDate;
    private LocalTime endTime;

    // example: deadline return book /by 02/08/2019 1800
    // only accept DD/MM/YYYY TTTT format
    public Deadline(String name, String endDateTime) {
        super(name);
        updateEndDateTime(endDateTime);
    }

    public Deadline(String name, LocalDate endDate, LocalTime endTime) {
        super(name);
        this.endDate = endDate;
        this.endTime = endTime;
    }

    public String getDateTime() {
        return this.endDate.toString() + " " + this.endTime.toString();
    }

    private void updateEndDateTime(String endDateTime) {
        StringBuilder year = new StringBuilder();
        StringBuilder month = new StringBuilder();
        StringBuilder day = new StringBuilder();
        StringBuilder time = new StringBuilder();

        Calendar currentlyAnalyzing = Calendar.DAY;
        for (int i = 0; i < endDateTime.length(); i++) {
            if (endDateTime.charAt(i) == '/' && currentlyAnalyzing.equals(Calendar.DAY)) {
                currentlyAnalyzing = Calendar.MONTH;
                continue;
            } else if (endDateTime.charAt(i) == '/' && currentlyAnalyzing.equals(Calendar.MONTH)) {
                currentlyAnalyzing = Calendar.YEAR;
                continue;
            } else if (endDateTime.charAt(i) == ' ' && currentlyAnalyzing.equals(Calendar.YEAR)) {
                currentlyAnalyzing = Calendar.TIME;
                continue;
            }

            if (currentlyAnalyzing.equals(Calendar.DAY)) {
                day.append(endDateTime.charAt(i));
            } else if (currentlyAnalyzing.equals(Calendar.MONTH)) {
                month.append(endDateTime.charAt(i));
            } else if (currentlyAnalyzing.equals(Calendar.YEAR)) {
                year.append(endDateTime.charAt(i));
            } else {
                time.append(endDateTime.charAt(i));
            }
        }

        int d = Integer.parseInt(year.toString()); // day
        int m = Integer.parseInt(month.toString()); // month
        int y = Integer.parseInt(day.toString()); // year
        LocalDate endDate = LocalDate.of(d, m, y);
        int hour = Integer.parseInt(time.toString().substring(0, 2));
        int minute = Integer.parseInt(time.toString().substring(2, 4));
        LocalTime endTime = LocalTime.of(hour, minute);

        this.endDate = endDate;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[D]" + getStatus() + " " + getName() + "(by: " + getDateTime() + ")";
    }
}
