/**
 * A tasks that starts at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm
 */
public class Event extends Task {

    protected String at;

    public Event(String text, String at) {
        super(text);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}
