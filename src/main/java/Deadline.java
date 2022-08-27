import java.time.LocalDate;

public class Deadline extends Task {
    protected String by;
//    protected LocalDate d;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

//    public Deadline(String description, LocalDate d) {
//        super(description);
//        this.d = d;
//    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
