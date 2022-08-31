package duke;

public class Response {
    private StringBuilder builder;

    public Response(StringBuilder builder) {
        this.builder = builder;
    }

    public void reset() {
        builder.setLength(0);
    }

    public void append(String input) {
        builder.append(input);
    }

    public String displayMessage() {
        return builder.toString();
    }
}
