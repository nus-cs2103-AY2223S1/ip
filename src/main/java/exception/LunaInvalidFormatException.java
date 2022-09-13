package exception;

/**
 * Represents a Luna exception when an invalid deadline/event format is entered.
 *
 * @author fannyjian
 */
public class LunaInvalidFormatException extends LunaException {
    @Override
    public String toString() {
        return "Luna only reads in specific formats 🌷";
    }
}
