import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private String by;
    private String taskType;
    private LocalDate deadline;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = "D";
        this.deadline = LocalDate.parse(by);
    }


    public String getDescription() {
        return super.getDescription() + " | " + by;
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString(){
        return String.format("[D]" + super.toString() + " (by: %s)",
                deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
