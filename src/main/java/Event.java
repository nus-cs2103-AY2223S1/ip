import java.util.Scanner;

public class Event extends Task {
    
    private String period;
    
    public Event(Scanner options) throws NoDescription, NoPeriod {
        if (options.hasNext()) {
            options.useDelimiter(" /at ");
            String description = options.next().substring(1);
            if (options.hasNext()) {
                String period = options.next();
                super.describe(description);
                super.unmark();
                this.period = period;
                System.out.println("CREATED EVENT: " + description + " AT: " + period);
            } else {
                throw new NoPeriod();
            }
        } else {
            throw new NoDescription();
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (At: " + period + ")";
    }
}
