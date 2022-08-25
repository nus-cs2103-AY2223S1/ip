package duke;

/**
 * The first testing class in the start of duke chatBot development.
 *
 */
public class Echo {
    private final String echoText;

    Echo(String echoText) {
        this.echoText = echoText;
    }

    @Override
    public String toString() {
        return this.echoText;
    }
}
