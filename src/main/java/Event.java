import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate time;

    public Event(String title, String time, boolean done) {
        super(title, "event", done);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.time = LocalDate.parse(time, formatter);
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return (super.toString() + " (at: " + this.time.format(formatter) + ")" );
    }

    public String getTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return this.time.format(formatter);
    }
}
