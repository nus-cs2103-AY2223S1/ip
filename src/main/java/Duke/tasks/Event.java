package Duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task {

    private final String deadline;
    private final String taskDesc;

    public Event(String taskDesc, String deadline) {
        super(taskDesc);
        this.taskDesc = taskDesc;
        this.deadline = deadline;
    }

    @Override
    public char getTaskType() {
        return 'E';
    }

    @Override
    public String getDesc() {
        return this.taskDesc + "|" + this.deadline;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + deadline + ")";
    }
}
