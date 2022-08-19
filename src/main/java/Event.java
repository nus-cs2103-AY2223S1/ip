/**
 * Events are tasks that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm
 */
public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, boolean completed) {
        super(description, completed);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toSaveString() {
        return "E," + super.toSaveString() + String.format(",%s", this.at);
    }

    public static Event parse(String data) throws ParsingTaskException {
        String[] components = data.split(",");
        if (components.length != 4) {
            throw new ParsingTaskException(String.format("Events require 4 components, but only found %d.", components.length));
        }
        try {
            boolean completed = Integer.parseInt(components[1]) == 1;

            String description = components[2];
            String at = components[3];

            return new Event(description, at, completed);
        } catch (NumberFormatException e) {
            throw new ParsingTaskException(String.format("Expected a number at component 1, but found %s", components[1]));
        }
    }
}