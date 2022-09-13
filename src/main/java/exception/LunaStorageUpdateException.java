package exception;

/**
 * Represents a Luna exception when an invalid number is entered.
 *
 * @author fannyjian
 */
public class LunaStorageUpdateException extends LunaException {
    @Override
    public String toString() {
        return "⚡️Luna has encountered an error while updating tasks⚡️"
                + "\n️Please exit and try again ️⛈";
    }
}
