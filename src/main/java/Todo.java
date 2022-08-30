import java.time.LocalDate;

public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String color = (isDone ? ANSI_GREEN : ANSI_RED);
        return color + "[T]" + super.toString() + ANSI_RESET;
    }

    @Override
    public String toFile() {
        int done = (isDone ? 1 : 0);
        return String.format("T | %d | %s", done, description);
    }

    public LocalDate getDateMaybe() {
        return null;
    }
}
