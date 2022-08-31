package duke;
import java.time.LocalDate;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public static ToDo createTodoFromString(String line) {
        return new ToDo(line.substring(10));
    }

    public boolean isOnThisDate(String dateStr) {
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
