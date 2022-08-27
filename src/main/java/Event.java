import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime timing;

    public Event(String input, String timing) {
        super(input, "");
        this.timing = getTime(timing);
    }

    public Event(String input, boolean done, String timing) {
        super(input, done, "");
        this.timing = getTime(timing);
    }

    public Event(String input, boolean done, LocalDateTime timing) {
        super(input, done, "");
        this.timing = timing;
    }
    @Override
    public String getTiming() {
        int day = this.timing.getDayOfMonth();
        Month month = this.timing.getMonth();
        int year = this.timing.getYear();
        int hour = this.timing.getHour();
        int min = this.timing.getMinute();
        return(String.format("%s %s %s %02d:%02d", day, month, year, hour, min));
    }

    public Event markDone() {
        return new Event(this.getVal(), true, this.timing);
    }

    public Event markUndone() {
        return new Event(this.getVal(), false, this.timing);
    }

    @Override
    public String toString() {
        if(this.getDone()) {
            return (String.format("[E][X] %s (%s)", this.getVal(), this.getTiming()));
        }
        else {
            return String.format("[E][ ] %s (%s)", this.getVal(), this.getTiming());
        }
    }
    private LocalDateTime getTime(String str) {
        //from stackoverflow
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return dateTime;
    }

    public boolean sameDay(LocalDateTime date) {
        return (this.timing.getDayOfMonth() == date.getDayOfMonth() &&
                this.timing.getMonth().equals(date.getMonth()) &&
                this.timing.getYear() == date.getYear());
    }
}
