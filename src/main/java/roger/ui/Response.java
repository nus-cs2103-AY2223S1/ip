package roger.ui;

public class Response {
    private String message;
    private boolean isExit;
    private boolean isException;

    public Response(String message, boolean isExit, boolean isException) {
        this.message = message;
        this.isExit = isExit;
        this.isException = isException;
    }

    public boolean isExit() {
        return isExit;
    }

    public boolean isException() {
        return isException;
    }

    public String getMessage() {
        return message;
    }
}
