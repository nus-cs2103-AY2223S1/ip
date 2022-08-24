package exception;

/**
 * Represents exceptions specific to Luna.
 *
 * @author fannyjian
 */
public class LunaException extends Exception {
    private static String sep = "\n✧  ✡︎✮ ✰ ✦ ✨️ ❍  ✫   ✣❈ ✶  ✧︎ ✱✬ ✨   ❇︎ ✫❍   ❈ ✶  ❍✶  ✯❃  ✨\n";

    /**
     * Creates a new exception with the error message.
     *
     * @param message Error message.
     */
    public LunaException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return sep + "\n" + getMessage() + "\n" + sep;
    }
}