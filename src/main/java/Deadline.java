import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_RESET = "\u001B[0m";
    protected String deadline;

    DateTimeFormatter dTF = DateTimeFormatter.ofPattern("MMM d yyyy");
    public Deadline(String description, String deadline, String taskType) {
        super(description, taskType);
        this.deadline = deadline;
    }
    public String getDeadline(){
        LocalDate d = LocalDate.parse(this.deadline);
        return d.format(dTF);
    }
    @Override
    public String toString() {
        return super.toString() + String.format(ANSI_RED + " (by: %s)", this.getDeadline() + ANSI_RESET);
    }
}
