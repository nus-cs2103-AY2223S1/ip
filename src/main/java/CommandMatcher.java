import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * This class serves as a way to abstract the idea of making a command
 * as a matching process and an action.
 */
public class CommandMatcher {
    private Predicate<String> check;
    private Consumer<String> action;
    CommandMatcher(Predicate<String> check, Consumer<String> action) {
        this.check = check;
        this.action = action;
    }

    CommandMatcher(String prefix, Consumer<String> action) {
        this.check = (cmd) -> cmd.startsWith(prefix);
        this.action = action;
    }

    /**
     * Checks if the string matches
     * If it does, it would execute the action
     * @param input The string to check if it is for this command
     * @return if the string matches
     */
    public boolean run(String input) {
        if (check.test(input)) {
            action.accept(input);
            return true;
        }
        return false;
    }
}
