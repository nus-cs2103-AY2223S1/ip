package anthea.exception;

import anthea.ChatbotResponse;

/**
 * An exception class that comes with a ChatbotResponse
 * so that the chatbot can show the message to the screen.
 */
public class ChatbotException extends Exception {
    private ChatbotResponse response;

    /**
     * Constructs a ChatbotException such that it
     * holds a ChatbotResponse.
     *
     * @param response Response which can be shown.
     */
    public ChatbotException(ChatbotResponse response) {
        super("[Exception]");
        assert response != null;
        this.response = response;
    }

    /**
     * Gets the available response.
     *
     * @return Single-use response.
     */
    public ChatbotResponse getResponse() {
        return response;
    }
}
