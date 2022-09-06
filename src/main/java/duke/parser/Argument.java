package duke.parser;

/**
 * This class encapsulates user arguments. Each argument consists
 * of two components, a name, along with body text.
 */
public class Argument {
    private final String name;
    private final String body;

    /**
     * Constructs an Argument class with the specified name parameter.
     * The body text of this argument will be null.
     *
     * @param name the specified name parameter.
     */
    public Argument(String name) {
        this.name = name;
        this.body = "";
    }

    /**
     * Constructs an Argument class with the specified name and body text parameter.
     *
     * @param name the specified name parameter.
     * @param body the specified body text parameter.
     */
    public Argument(String name, String body) {
        this.name = name;
        this.body = body;
    }

    public String getName() {
        return this.name;
    }

    public String getBody() {
        return this.body;
    }

    /**
     * Returns a String representation of the user argument.
     *
     * @return a String.
     */
    @Override
    public String toString() {
        return String.format("Argument %s: %s", this.name, this.body);
    }
}
