package exception;

/**
 * Represents a Luna exception when an invalid date is entered.
 *
 * @author fannyjian
 */
public class LunaInvalidDateException extends LunaException {
    @Override
    public String toString() {
        return "Luna only reads dates in yyyy-MM-dd format 🍂";
    }
}
