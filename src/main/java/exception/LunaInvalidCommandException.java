package exception;

/**
 * Represents a Luna exception when an invalid command is entered.
 *
 * @author fannyjian
 */
public class LunaInvalidCommandException extends LunaException {
    @Override
    public String toString() {
        return "Luna doesn't understand 🥀";
    }
}
