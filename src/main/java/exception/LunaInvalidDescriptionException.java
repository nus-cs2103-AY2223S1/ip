package exception;

/**
 * Represents a Luna exception when an invalid task description is entered.
 *
 * @author fannyjian
 */
public class LunaInvalidDescriptionException extends LunaException {
    @Override
    public String toString() {
        return "Luna wants to know what your task is about 🌷";
    }
}
