/**
 * Deadlines are tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean completed) {
        super(description, completed);
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toSaveString() {
        return "D," + super.toSaveString() + String.format(",%s", this.by);
    }

    public static Deadline parse(String data) throws ParsingTaskException {
        String[] components = data.split(",");
        if (components.length != 4) {
            throw new ParsingTaskException(String.format("Todos require 4 components, but found %d.", components.length));
        }
        try {
            boolean completed = Integer.parseInt(components[1]) == 1;

            String description = components[2];
            String by = components[3];

            return new Deadline(description, by, completed);
        } catch (NumberFormatException e) {
            throw new ParsingTaskException(String.format("Expected a number at component 1, but found %s", components[1]));
        }
    }
}