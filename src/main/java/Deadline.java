package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
class Deadline extends Task {

    private LocalDate time;
    private String timeInString;

    Deadline(String description, String Date) {
        super(description);
        this.time = LocalDate.parse(Date);
        this.timeInString = Date;
    }

    public String toString() {
        return "[D]" + super.toString() + " by " + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String write() {
        return "D" + super.write() + ":" + this.timeInString;
    }

}