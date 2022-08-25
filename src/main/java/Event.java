import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Event extends Task{
    protected LocalDate time;

    public Event(String itself, String time) {
        super(itself);
        this.time = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public boolean isOnDate(LocalDate localDate) {
        return time.equals(localDate);
    }

    @Override
    public String writeToFile() {
        return "E|" + super.writeToFile() + "|" + time;
    }

    public static Event fromFileDescription(String input) {
        String[] strArray = input.split("\\|");
        String info = strArray[2];
        String at = strArray[3];
        Event event = new Event(info, at);
        if (strArray[1].equals("Y")) {
            event.donelah();
        }
        return event;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                time.format(DateTimeFormatter.ofPattern("MMMM d yyyy")) + ")";
    }
}
