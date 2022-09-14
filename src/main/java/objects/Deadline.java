package objects;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a Task object with end date and end time, which literally
 * serves as the 'deadline'.
 */
public class Deadline extends Task {

    private enum Calendar {
        YEAR, MONTH, DAY, TIME
    }

    private LocalDate endDate;
    private LocalTime endTime;

    /**
     * Constructor for Deadline object that takes in name and endDateTime.
     * Example: deadline return book /by 02/08/2019 18:00
     * The time TT:TT is in 24-hour format.
     *
     * @param name description of the Deadline object
     * @param endDateTime date and time for the Deadline object
     */
    public Deadline(String name, String endDateTime) {
        super(name.trim());
        updateEndDateTime(endDateTime);
    }

    public Deadline(String name, LocalDate endDate, LocalTime endTime) {
        super(name.trim());
        this.endDate = endDate;
        this.endTime = endTime;
    }

    public String getDateTime() {
        return this.endDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString()
                + " " + LocalTime.parse(this.endTime.toString(), DateTimeFormatter.ofPattern("HH:mm")).toString();
    }

    // endDateTime must follow either "DD/MM/YYYY" or "DD/MM/YYYY TT:TT"
    // where TT:TT is the time in 24-hour format
    // if time is not included, the default value of 00:00 will be used
    private void updateEndDateTime(String endDateTime) {
        // Initializing variables needed
        StringBuilder year = new StringBuilder();
        StringBuilder month = new StringBuilder();
        StringBuilder day = new StringBuilder();
        StringBuilder time = new StringBuilder();
        Calendar currentlyAnalyzing = Calendar.DAY;

        // Updates the variables by parsing endDateTime string
        parseEndDateTime(endDateTime, currentlyAnalyzing, day, month, year, time);
        // Obtain the required values
        this.endDate = getEndDate(day, month, year);
        this.endTime = getEndTime(time); // value of endTime defaults to 00:00 if no time is indicated
    }

    private void parseEndDateTime(String endDateTime, Calendar currentlyAnalyzing,
                                  StringBuilder day, StringBuilder month, StringBuilder year, StringBuilder time) {
        for (int i = 0; i < endDateTime.length(); i++) {
            if (endDateTime.charAt(i) == '/' && currentlyAnalyzing.equals(Calendar.DAY)) {
                currentlyAnalyzing = Calendar.MONTH;
                continue;
            } else if (endDateTime.charAt(i) == '/' && currentlyAnalyzing.equals(Calendar.MONTH)) {
                currentlyAnalyzing = Calendar.YEAR;
                continue;
            } else if (endDateTime.charAt(i) == ' ') {
                // deadline return book /by 02/08/2019 18:00
                currentlyAnalyzing = Calendar.TIME;
                continue;
            }

            // Build the string for the relevant StringBuilder
            if (currentlyAnalyzing.equals(Calendar.DAY)) {
                day.append(endDateTime.charAt(i));
            } else if (currentlyAnalyzing.equals(Calendar.MONTH)) {
                month.append(endDateTime.charAt(i));
            } else if (currentlyAnalyzing.equals(Calendar.YEAR)) {
                year.append(endDateTime.charAt(i));
            } else if (currentlyAnalyzing.equals(Calendar.TIME)) {
                time.append(endDateTime.charAt(i));
            }
        }
    }

    private LocalDate getEndDate(StringBuilder day, StringBuilder month, StringBuilder year) {
        int y = Integer.parseInt(year.toString()); // year
        int m = Integer.parseInt(month.toString()); // month
        int d = Integer.parseInt(day.toString()); // day
        LocalDate endDate = LocalDate.of(y, m, d);
        return endDate;
    }

    private LocalTime getEndTime(StringBuilder time) {
        LocalTime endTime = LocalTime.of(0, 0);
        if (time.length() != 0) {
            int hour = Integer.parseInt(time.toString().substring(0, 2));
            int minute = Integer.parseInt(time.toString().substring(3, 5));
            endTime = LocalTime.parse(LocalTime.of(hour, minute).toString(), DateTimeFormatter.ofPattern("HH:mm"));
        }
        return endTime;
    }

    @Override
    public String toString() {
        return "[D]" + getStatus() + " " + getName() + " (by: " + getDateTime() + ")";
    }
}
