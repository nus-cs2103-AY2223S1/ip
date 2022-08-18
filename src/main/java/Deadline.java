import java.util.NoSuchElementException;
import java.util.Scanner;

public class Deadline extends Task {
    private String deadline;

    public Deadline(Scanner options) throws MissingOptions, NoSuchElementException {
        super(options.next().substring(1));
        if (options.hasNext()) {
            this.deadline = options.next();
        } else {
            throw new MissingOptions("no deadline set");
        }
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
