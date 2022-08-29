package exception;

import task.TaskList;

/**
 * Represents a Luna exception when an invalid number is entered.
 *
 * @author fannyjian
 */
public class LunaInvalidIndexException extends LunaException {
    @Override
    public String toString() {
        return "Luna only reads numbers from 0 to " + TaskList.size() + " for this command 🍂";
    }
}
