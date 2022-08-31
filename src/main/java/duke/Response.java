package duke;

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
