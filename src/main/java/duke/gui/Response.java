package duke.gui;

public class Response {
    private ResponseType responseType;
    private String message;

    public Response(ResponseType responseType, String responseMessage) {
        this.responseType = responseType;
        message = responseMessage;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public String getResponseMessage() {
        return message;
    }
}
