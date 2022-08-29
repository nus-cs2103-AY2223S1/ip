package ekud.util;

public class ParseResult {
    public final boolean terminate;
    public final String message;
    public final boolean saveStorage;

    /**
     * Constructor that instantiates instance of ParseResult.
     * 
     * @param terminate Boolean representing if program should terminate.
     * @param message Message to be printed to the user.
     * @param saveStorage Boolean representing if task list should be re-saved to
     *        storage.
     */
    public ParseResult(boolean terminate, String message, boolean saveStorage) {
        this.terminate = terminate;
        this.message = message;
        this.saveStorage = saveStorage;
    }
}
