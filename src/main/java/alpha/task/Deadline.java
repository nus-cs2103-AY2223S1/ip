package alpha.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_RESET = "\u001B[0m";
    protected String deadline;

    DateTimeFormatter dTF = DateTimeFormatter.ofPattern("MMM d yyyy");
    public Deadline(String description, String deadline, String taskType) {
        super(description, taskType);
        this.deadline = deadline;
    }
    public String getDeadline(){
        return this.deadline;
    }
    @Override
    public String toString() {
        LocalDate d = LocalDate.parse(this.deadline);
        String formattedDeadline = d.format(dTF);
        return super.toString() + String.format(ANSI_RED + " (by: %s)", formattedDeadline + ANSI_RESET);
    }
}
