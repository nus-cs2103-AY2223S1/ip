import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by); //Obtains an instance of LocalDateTime from a text string such as 2007-12-03T10:15:30.
    }

    @Override
    public String saveString() {
        return "D | " + (this.isDone ? "1 | " : "0 | ") + this.description + " | " + this.by;
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + 
                " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm:ss")) + ")";
    }

}
