import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Event extends Task{
    protected LocalDate time;

    public Event(String itself, String time) {
        super(itself);
        this.time = LocalDate.parse(time);
    }

    public boolean isOnDate(LocalDate localDate) {
        return time.equals(localDate);
    }

    @Override
    public String writeToFile() {
        return "E|" + super.writeToFile() + "|" + time.trim();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ")";
    }
}
