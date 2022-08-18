import java.util.NoSuchElementException;
import java.util.Scanner;

public class Event extends Task {
    private String period;

	public Event(Scanner options) throws MissingOptions, NoSuchElementException {
        super(options.next().substring(1));
        if (options.hasNext()) {
            this.period = options.next();
        } else {
            throw new MissingOptions("no deadline set");
        }
	}
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + period + ")";
    }
}
