import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
class Event extends Task {
    protected formatDate date;

    public Event(String description, String at) {
        super(description);
        this.date = new formatDate(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: "+ this.date + ")";
    }

    @Override
    public String toSave() {
        return "E" + super.toSave() + this.description + "| " + this.at;
    }
}
