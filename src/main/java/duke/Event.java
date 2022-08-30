package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {

    protected LocalDate date;
    protected LocalTime time;

    public Event(String description, boolean isDone, String timeline) {
        super(description, isDone);
        String[] splitStr = timeline.trim().split("\\s+");
        LocalDate date = LocalDate.parse(splitStr[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalTime time = LocalTime.parse(splitStr[1], DateTimeFormatter.ofPattern("HH:mm"));
        this.date = date;
        this.time = time;
    }

    /**
     * Converts the event in tasks.txt into an Event object.
     */
    public static Event parse(String task) {
        boolean isDone = task.substring(4, 5).equals(" ") ? false : true;
        Pattern taskPattern = Pattern.compile("] (.*?) \\(at");
        Matcher taskMatcher = taskPattern.matcher(task);
        taskMatcher.find();
        Pattern timePattern = Pattern.compile("at: (.*?)\\)");
        Matcher timeMatcher = timePattern.matcher(task);
        timeMatcher.find();
        String time = LocalDateTime.parse(timeMatcher.group(1), DateTimeFormatter.ofPattern("d MMM yyyy HHmm", Locale.ENGLISH))
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        return new Event(taskMatcher.group(1), isDone, time);
    }

    @Override
    public String toString() {
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy").withLocale(Locale.ENGLISH));
        String formattedTime = this.time.format(DateTimeFormatter.ofPattern("HHmm"));
        //[D][ ] task (by: dd MMM yyyy HHmm)
        return "[E]" + super.toString() + " (at: " + formattedDate + " " + formattedTime + ")";
    }
}