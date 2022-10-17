package duke.exception;

import duke.DukeResponse;

/**
 * An exception class that comes with a DukeResponse
 * so that the chatbot can show the message to the screen.
 */
public class DukeException extends Exception {
    private DukeResponse response;

    /**
     * Constructs a DukeException such that it
     * holds a DukeResponse.
     *
     * @param response Response which can be shown.
     */
    public DukeException(DukeResponse response) {
        super("[DukeException]");
        assert response != null;
        this.response = response;
    }

    /**
     * Gets the available response.
     *
     * @return Single-use response.
     */
    public DukeResponse getResponse() {
        return response;
    }
}
