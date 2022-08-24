import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate date;
    private static final DateTimeFormatter FORMAT = DateTimeFormatter
            .ofPattern("dd/MM/yyyy");

    /**
     * Constructor for Deadline class
     * @param description Description of deadline
     * @param date Due date of deadline
     */
    public Deadline(String description, Boolean isDone, String date) {
        super(description);
        if (isDone) {
            super.markAsDone();
        }

        this.date = LocalDate.parse(date, FORMAT);
    }

    @Override
    public String saveData() {
        String isDone;
        if (super.isDone) {
            isDone = "O";
        } else {
            isDone = "X";
        }
        return String.format("D | %s | %s | %s\n", isDone, super.description,
                date.format(FORMAT));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
