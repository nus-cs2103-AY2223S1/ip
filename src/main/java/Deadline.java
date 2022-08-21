import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate time;

    public Deadline(String taskname, LocalDate time) {
        super(taskname);
        this.time = time;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy");
        return "[D]" + super.toString() + " (by: " + this.time.format(formatter) + ")";
    }
}
