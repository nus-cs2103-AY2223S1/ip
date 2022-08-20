import java.time.LocalDate;

public class ToDo extends Task {

    public ToDo(String description, TaskType type) {
        super(description, type);
    }

    @Override
    public boolean isDateEqual(LocalDate date) {
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
