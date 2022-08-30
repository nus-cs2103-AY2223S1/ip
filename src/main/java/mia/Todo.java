package mia;

/**
 * A {@code Todo} is a {@code Task} with no additional functionality.
 *
 * @author Richard Dominick
 */
public class Todo extends Task {

    Todo(String title, boolean isCompleted) {
        super(title, isCompleted);
    }

    Todo(String title) {
        super(title);
    }

    /**
     * Parses and creates a new {@code Todo} instance from a {@code String} representing its data.
     *
     * @param saveFormat The string representing the data from which to create a new {@code Todo} instance
     * @return A new {@code Todo} instance based on the parsed data
     * @throws IllegalArgumentException When the save format is invalid
     */
    public static Todo fromSaveFormat(String saveFormat) throws IllegalArgumentException {
        final String[] args = saveFormat.split(";;");
        if (args.length != 2) {
            throw new IllegalArgumentException("Incorrect save format: " + saveFormat);
        }
        return new Todo(args[1], args[0].equals("1"));
    }

    @Override
    public String toSaveFormat() {
        return String.format("T;;%s", super.toSaveFormat());
    }

    @Override
    public String toString() {
        return String.format("📜 %s", super.toString());
    }
}
