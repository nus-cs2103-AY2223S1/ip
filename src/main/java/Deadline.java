import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private final String tag = "[D]";
    private final LocalDate date;

    public Deadline(String name, String deadline) {
        super(name);
        this.date = LocalDate.parse(deadline);

    }


    @Override
    public String toString() {
        return tag + "[" + this.getStatusIcon()  + "] " + this.getTaskName() + "(" + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
