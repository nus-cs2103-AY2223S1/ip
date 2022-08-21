import java.time.LocalDate;

public class DatedTask extends Task{
    protected LocalDate date;
    protected DatedTask(String description, LocalDate date) {
        super(description);
        this.date = date;
    }
}
