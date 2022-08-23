import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class that encapsulate a deadline task
 */
public class Deadline extends Task {

    private String by;

    private LocalDate deadline;

    public Deadline(String description, String by) throws TedException {
        super(description);
        this.by = by;

        try {
            if (this.by.toLowerCase().equals("today")) {
                this.deadline = LocalDate.now();
            } else if (this.by.toLowerCase().equals("tomorrow")) {
                this.deadline = LocalDate.now().plusDays(1);
            } else {
                this.deadline = LocalDate.parse(this.by);
            }
        } catch (DateTimeParseException e) {
            throw new TedException("Date is not in yyyy-mm-dd format");
        }
    }

    @Override
    public String encode() {
        return String.format("D | %s | %s", super.encode(), this.by);
    }

    @Override
    public String toString() {
        String deadlineStr = "";

        if (isSameDate(deadline, LocalDate.now())) {
            deadlineStr = "today";
        } else if (isSameDate(deadline, LocalDate.now().plusDays(1))) {
            deadlineStr = "tomorrow";
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            deadlineStr = deadline.format(formatter);
        }

        return "[D]" + super.toString() + " (by: " + deadlineStr + ")";
    }

    private boolean isSameDate(LocalDate date1, LocalDate date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("Date must not be null.");
        }

        return date1.getYear() == date2.getYear() &&
                date1.getDayOfYear() == date2.getDayOfYear();
    }
}