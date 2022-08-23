import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate time;

    public Deadline(String name, LocalDate time) {
        super(name);
        this.time = time;
    }

    @Override
    public String stringify() {
        return "D##" + super.stringify() + "##" + this.time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

