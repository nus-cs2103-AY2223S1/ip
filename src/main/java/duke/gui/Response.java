package duke.gui;

public class Response<T> {
    private ResponseType responseType;
    private T responseObject;
    private String responseMessage;

    public Response(ResponseType responseType, String responseMessage) {
        this.responseType = responseType;
        this.responseMessage = responseMessage;
    }

    public Response(ResponseType responseType, String responseMessage, T responseObject) {
        this.responseType = responseType;
        this.responseMessage = responseMessage;
        this.responseObject = responseObject;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public String getResponseMessage() { return responseMessage; }

    public T getResponseObject() {
        return responseObject;
    }
}
