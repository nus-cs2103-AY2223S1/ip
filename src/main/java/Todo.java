import java.time.LocalDateTime;
import java.util.Optional;

public class Todo extends Task {
    public Todo(String description, boolean done) {
        super(description, done);
    }

    @Override
    public Optional<LocalDateTime> getTime() {
        return Optional.empty();
    }

    @Override
    public String getTaskType() {
        return "Todo";
    }

    @Override
    public String toString() {
        String checkbox = this.getDone() ? "[T][X]" : "[T][ ]";
        return checkbox + " " + super.getDescription();
    }
}
