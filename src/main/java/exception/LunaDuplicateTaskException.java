package exception;

/**
 * Represents a Luna exception when duplicate task is added.
 *
 * @author fannyjian
 */
public class LunaDuplicateTaskException extends LunaException {
    @Override
    public String toString() {
        return "Luna finds the same task in your list 🍂";
    }
}
