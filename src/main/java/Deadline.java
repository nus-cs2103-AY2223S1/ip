import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public boolean isOn(LocalDate date) {
        return this.by.equals(date);
    }

    @Override
    public String toFileRepresentation() {
        return String.format("D | %s | %s", super.toFileRepresentation(), this.by);
    }

    public static Deadline fromFileRepresentation(String rep) {
        String[] args = rep.split(" \\| ");
        boolean isDone = args[1].equals("1");
        String description = args[2];
        String date = args[3];
        Deadline result = new Deadline(description, LocalDate.parse(date));
        if (isDone) {
            result.markDone();
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.by.format(DateTimeFormatter.ofPattern("E, d MMM yyyy")));
    }
}
