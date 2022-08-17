import java.time.LocalDate;

public class Event extends Task{
    private LocalDate at;

    Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("%s%s(at: %s)", "[E]", super.toString(), this.at);
    }
}
