package duke;

/**
 * A generic user interface handler.
 */
public abstract class Ui {
    private InputAcceptor inputAcceptor;
    /**
     * Creates a new UI handler.
     */
    public Ui(InputAcceptor ia) {
        assert ia != null;
        inputAcceptor = ia;
    }

    /**
     * Starts the user interface input loop.
     */
    public abstract void runInputLoop();

    /**
     * Stops the user interface input loop.
     */
    public abstract void stopInputLoop();

    /**
     *
     */
    public void processInput(String input) {
        inputAcceptor.input(input);
    }

    /**
     * Display some output to the user.
     * @param response The output to be shown.
     */
    public abstract void respond(String response);
}
