import java.time.LocalDate;

public class ToDos extends Task{
    public ToDos(String description) {
        super (description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean checkDate(LocalDate anoDate) {
        return false;
    }
}
