package Duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {

    private final String date;
    private final String taskDesc;

    public Deadline(String taskDesc, String date) {
        super(taskDesc);
        this.taskDesc = taskDesc;
        this.date = date;
    }

    @Override
    public char getTaskType() {
        return 'D';
    }

    @Override
    public String getDesc() {
        return this.taskDesc + "|" + this.date;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
