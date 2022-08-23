import java.time.LocalDate;

public class Todo extends Task {

    Todo(String description) {
        super(description);
    }

    public boolean isHappeningOnDate(LocalDate localDate) {
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
