import java.time.LocalDate;

public class DeadlineTask extends Task {
    protected LocalDate by;

    public DeadlineTask(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String formattedDate = by.getMonth() + " " + by.getDayOfMonth() + " " + by.getYear();
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
