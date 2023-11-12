package byu.util;

/**
 * A Response class to indicate Byu's response to the user input, consisting of the output and  actions to be taken.
 */
public class Response {
    private final String output;
    private final boolean isExit;
    private final boolean isHelp;

    /**
     * Creates an instance of {@code Response}.
     *
     * @param output the string indicating the response.
     * @param isExit if {@code this} responds to an {@code ExitCommand}.
     * @param isHelp if {@code this} responds to a {@code HelpCommand}.
     */
    public Response(String output, boolean isExit, boolean isHelp) {
        this.output = output;
        this.isExit = isExit;
        this.isHelp = isHelp;
    }
    public boolean isExit() {
        return isExit;
    }

    public boolean isHelp() {
        return isHelp;
    }
    public String getOutput() {
        return this.output;
    }
}
