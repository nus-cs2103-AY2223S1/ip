import java.time.LocalDate;
import java.lang.NumberFormatException;

public class Deadline extends Task {
    private LocalDate deadline;
    public Deadline(String description, String deadline) throws DukeException {
        super(description);
        String[] dateInfo = deadline.split("-");
        if (dateInfo.length < 3) {
            throw new DukeException("Please use format yyyy-mm-dd for your deadline!");
        }
        try {
            this.deadline = LocalDate.of(Integer.valueOf(dateInfo[0]), Integer.valueOf(dateInfo[1]), Integer.valueOf(dateInfo[2]));
        } catch (NumberFormatException e) {
            throw new DukeException("Please use format yyyy-mm-dd for your deadline, and only use numbers!");
        }
    }

    @Override
    public String toString() {
        return "  [D] [" + this.getStatusIcon() + "] " + this.description + " (by: " + this.deadline + ")";
    }
}
