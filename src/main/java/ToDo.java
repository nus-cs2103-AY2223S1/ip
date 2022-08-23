import java.time.LocalDate;

public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public LocalDate getTime() {
        return null;
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
