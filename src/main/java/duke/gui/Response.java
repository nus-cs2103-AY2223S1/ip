package duke.gui;

public class Response<T> {
    private ResponseType responseType;
    private T responseObject;

    public Response(ResponseType responseType, T responseObject) {
        this.responseType = responseType;
        this.responseObject = responseObject;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public T getResponseMessage() {
        return responseObject;
    }
}
