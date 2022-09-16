package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {

    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        String[] splitStr = by.trim().split("\\s+");
        LocalDate date = LocalDate.parse(splitStr[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalTime time = LocalTime.parse(splitStr[1], DateTimeFormatter.ofPattern("HH:mm"));
        this.date = date;
        this.time = time;
    }


    /**
     * Converts the deadline in tasks.txt into a Deadline object.
     */
    public static Deadline parse(String task) {
        boolean isDone = task.substring(4, 5).equals(" ") ? false : true;
        Pattern pattern = Pattern.compile("\\] (?<description>[^\\(]*)\\(by: (?<time>.*)\\)");
        Matcher matcher = pattern.matcher(task);
        matcher.find();
        String description = matcher.group("description");
        String time = matcher.group("time");
        time = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("d MMM yyyy HHmm", Locale.ENGLISH))
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        return new Deadline(description, isDone, time);
    }

    @Override
    public String toString() {
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy").withLocale(Locale.ENGLISH));
        String formattedTime = this.time.format(DateTimeFormatter.ofPattern("HHmm"));
        // [D][ ] task (by: dd MMM yyyy HHmm)
        return "[D]" + super.toString() + " (by: " + formattedDate + " " + formattedTime + ")";
    }
}