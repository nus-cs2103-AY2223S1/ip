package deku;

/**
 * Custom exception class for bot when faced with unexpected results
 */
public class DekuExceptions extends Exception {
    private final String message;

    /**
     * Constructor to initialise the DukeExeptions class
     *
     * @param errorMsg Output message the bot will throw
     *
     */
    public DekuExceptions(String errorMsg) {
        super("AUUUUUGH! "
              + errorMsg);
        message = errorMsg;
    }

    @Override
    public String toString() {
        return "AUUUUUGH! "
               + message;
    }
}
