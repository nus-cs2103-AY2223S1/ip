import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TaskDeadline extends Task{

    private LocalDate deadline;

    public TaskDeadline(String name, boolean done, LocalDate deadline) throws TaskNoNameException {
        super(name, done);
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
