package duke.response;

/**
 * Represents the Response to some user input.
 */
public class Response {

    private String message;

    public Response(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
