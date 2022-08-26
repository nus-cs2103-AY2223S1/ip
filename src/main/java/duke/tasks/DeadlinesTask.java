package duke.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlinesTask extends Task {
    private LocalDateTime deadline;

    public DeadlinesTask(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }



    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "[" + TaskType.D +"]" + "[" + this.getStatusIcon() + "] " + this.getName() +  " (by: " + this.getDeadline().format(formatter) + ")";
    }

    @Override
    public String toCSV() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return TaskType.D + "," + this.getStatusIcon()  + "," + this.getName() + "," + this.getDeadline().format(formatter);
    }
}
