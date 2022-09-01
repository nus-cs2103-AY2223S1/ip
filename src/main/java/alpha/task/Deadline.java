package alpha.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_RESET = "\u001B[0m";
    protected String deadline;

    public Deadline(String description, String deadline, String taskType) {
        super(description, taskType);
        this.deadline = deadline;
    }
    public String getDeadline(){
        return this.deadline;
    }
    @Override
    public String toString() throws DateTimeException {
        return super.toString() + String.format(ANSI_RED + " (by: %s)", deadline + ANSI_RESET);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj instanceof Deadline) {
            Deadline d = (Deadline) obj;
            return (d.getDescription().equals(this.getDescription()) && d.getStatus().equals(this.getStatus()) && d.taskType.equals(this.taskType) && d.getDeadline().equals(this.getDeadline()));
        }
        return false;
    }
}
