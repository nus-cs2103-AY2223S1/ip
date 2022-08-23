import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    protected LocalDate by;

    public Deadlines(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String addString(int i) {
        String line = "_______________________________\n";
        String gotIt = "Got it. I've added this task: \n";
        String task = this.toString() + "\n";
        String now = String.format("Now you have %d tasks in the list \n", i);
        return line + gotIt + task + now + line;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }


    @Override
    public String saveString() {
        if (this.isDone) {
            return "D" + "X " + this.description + "/by" + this.by + "\n";
        } else {
            return "D  " + this.description + "/by" + this.by + "\n";
        }
    }
}
