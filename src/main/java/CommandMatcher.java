import java.util.function.Consumer;
import java.util.function.Predicate;

public class CommandMatcher {
    private Predicate<String> check;
    private Consumer<String> action;
    CommandMatcher(Predicate<String> check, Consumer<String> action) {
        this.check = check;
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
