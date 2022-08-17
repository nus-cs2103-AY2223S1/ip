import java.time.LocalDate;

public class Event extends Task{
    private LocalDate at;

    Event(String description, LocalDate at) {
        super(description, TaskType.EVENT);
        this.at = at;
    }

    @Override
    String getBy() {
        return this.at;
    }

    @Override
    public String toString() {
        return String.format("%s%s(at: %s)", "[E]", super.toString(), this.at);
    }
}
