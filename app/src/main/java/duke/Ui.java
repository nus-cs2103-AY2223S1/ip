package duke;

public abstract class Ui {
    /**
     * Creates a new UI handler.
     */
    public Ui() {
    }

    /**
     * Gets a line of user input.
     * @return The user input.
     */
    public abstract String getLine();

    /**
     * Display some output to the user.
     * @param response The output to be shown.
     */
    public abstract void respond(String response);
}
