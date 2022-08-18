import java.util.Scanner;

public class Deadline extends Task {
    private String deadline;
    
    public Deadline(Scanner options) throws NoDescription, NoDeadline {
        if (options.hasNext()) {
            options.useDelimiter(" /by ");
            String description = options.next().substring(1);
            if (options.hasNext()) {
                String deadline = options.next();
                super.describe(description);
                super.unmark();
                this.deadline = deadline;
                System.out.println("CREATED DEADLINE: " + description + " DUE ON: " + deadline);
            } else {
                throw new NoDeadline();
            }
        } else {
            throw new NoDescription();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (Deadline: " + deadline + ")";
    }
}
