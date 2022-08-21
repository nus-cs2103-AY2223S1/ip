package maria.task;

import maria.util.DukeDateTimeFormatter;

import java.time.LocalDate;

public class TaskDeadline extends Task{

    private LocalDate deadline;

    public TaskDeadline(String name, boolean isDone, LocalDate deadline) throws TaskNoNameException {
        super(name, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DukeDateTimeFormatter.formatDisplay(this.deadline) + ")";
    }

    @Override
    public String toStorageString() {
        return super.toStorageString() + "|||" + "deadline" + "|||" +
                DukeDateTimeFormatter.formatStorage(this.deadline);
    }

}
