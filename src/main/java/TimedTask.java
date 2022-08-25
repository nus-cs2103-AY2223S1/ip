import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

abstract class TimedTask extends Task {
    protected static String format = "dd/MM/yyyy HH:mm";
    protected LocalDateTime time;

    public TimedTask(String description, String rawDateTime) throws DukeException {
        super(description);
        this.time = convertRawTime(rawDateTime);
    }

    public static void setFormat(String format) throws DukeException {
        if (format.isEmpty()) throw new DukeException("Format cannot be blank.");
        try {
            DateTimeFormatter.ofPattern(format);
        } catch (IllegalArgumentException e) {
            throw new DukeException("Invalid format %s.", format);
        }
        TimedTask.format = format;
    }
    
    public String getFormattedTime() {
        return time.format(DateTimeFormatter.ofPattern(format));
    }

    public LocalDateTime convertRawTime(String rawDateTime) throws DukeException {
        LocalDateTime time;
        try {
            time = LocalDateTime.parse(rawDateTime, DateTimeFormatter.ofPattern(format));
        } catch (DateTimeParseException e1) {
            try {
                time = LocalDateTime.of(LocalDate.parse(rawDateTime, DateTimeFormatter.ofPattern(format.split(" ")[0])), LocalTime.MIDNIGHT);
            } catch (DateTimeParseException e2) {
                try {
                    time = LocalDateTime.of(LocalDate.now(), LocalTime.parse(rawDateTime, DateTimeFormatter.ofPattern(format.split(" ")[1])));
                } catch (DateTimeParseException e3) {
                    throw new DukeException("Wrong datetime format. Please input datetime in the format dd/MM/yyyy HH:mm");
                }
            }
        }
        return time;
    }

    @Override
    public int compareTo(Task other) {
        if (!(other instanceof TimedTask)) {
            return -1;
        }
        TimedTask otherTimedTask = (TimedTask) other;
        if (time.compareTo(otherTimedTask.time) != 0) {
            return time.compareTo(otherTimedTask.time);
        } else {
            return super.compareTo(other);
        }
    }
}
