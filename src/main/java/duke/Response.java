package duke;

/**
 * Contains information for how the chatbot builds its response.
 */
public class Response {
    private StringBuilder builder;

    /**
     * Constructor for a new Response.
     *
     * @param builder Stringbuilder used to handle string inputs.
     */
    public Response(StringBuilder builder) {
        this.builder = builder;
    }

    /**
     * Clears the response saved inside Response.
     */
    public void reset() {
        builder.setLength(0);
    }

    /**
     * Appends the chatbot's response onto the current string.
     *
     * @param input The string to concatenate onto the current string.
     */
    public void append(String input) {
        builder.append(input);
    }

    /**
     * Displays the response saved inside Response.
     */
    public String displayMessage() {
        return builder.toString();
    }
}
