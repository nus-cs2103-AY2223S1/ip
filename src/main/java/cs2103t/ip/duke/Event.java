package cs2103t.ip.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate at;
    private final String LINE = "_______________________________\n";

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String addString(int i) {
        String gotIt = "Got it. I've added this task: \n";
        String task = this.toString() + "\n";
        String now = String.format("Now you have %d tasks in the list \n", i);
        return LINE + gotIt + task + now + LINE;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String saveString() {
        if (this.isDone) {
            return "E" + "X " + this.description + "/at" + this.at + "\n";
        } else {
            return "E  " + this.description + "/at" + this.at + "\n";
        }
    }
}
