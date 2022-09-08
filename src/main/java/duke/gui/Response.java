package duke.gui;

/**
 * Class representing a Duke response object. Response objects have a
 * response type, which indicate the type of response, and a response message.
 * They may also contain response objects.
 *
 * @param <T> The type of the object contained by the response object.
 */
public class Response<T> {
    private final ResponseType responseType;
    private T responseObject;
    private final String responseMessage;

    /**
     * Constructs a response object of the specified type, with the specified message.
     *
     * @param responseType The type of response.
     * @param responseMessage The response message.
     */
    public Response(ResponseType responseType, String responseMessage) {
        this.responseType = responseType;
        this.responseMessage = responseMessage;
    }

    /**
     * Constructs a response object of the specified type, with the specified message, which
     * contains the specified response object.
     *
     * @param responseType The type of response.
     * @param responseMessage The response message.
     * @param responseObject The object contained by the response.
     */
    public Response(ResponseType responseType, String responseMessage, T responseObject) {
        this.responseType = responseType;
        this.responseMessage = responseMessage;
        this.responseObject = responseObject;
    }

    /**
     * Gets the response type of the response.
     *
     * @return The response type of the response.
     */
    public ResponseType getResponseType() {
        return responseType;
    }

    /**
     * Gets the response message of the response.
     *
     * @return The response message of the response.
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * Gets the response object contained by the response.
     *
     * @return The response object contained by the response.
     */
    public T getResponseObject() {
        return responseObject;
    }
}
