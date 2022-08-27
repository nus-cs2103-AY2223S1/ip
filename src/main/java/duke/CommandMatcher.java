package duke;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * This class serves as a way to abstract the idea of making a command
 * as a matching process and an action.
 */
public class CommandMatcher {
    private Predicate<String> shouldRunAction;
    private Consumer<String> action;

    /**
     * Creates an object that handles checking and executing a command.
     * @param shouldRunAction Predicate to check if the command should be run.
     * @param action Action to run.
     */
    public CommandMatcher(Predicate<String> shouldRunAction, Consumer<String> action) {
        this.shouldRunAction = shouldRunAction;
        this.action = action;
    }

    /**
     * Creates an object that handles checking and executing a command.
     * @param prefix Prefix of the command which is checked.
     * @param action Action to run.
     */
    public CommandMatcher(String prefix, Consumer<String> action) {
        this.shouldRunAction = (cmd) -> cmd.strip().startsWith(prefix);
        this.action = action;
    }

    /**
     * Checks if the string matches.
     * If it does, it would execute the action.
     * @param input String to check if it is for this command.
     * @return If the string matches.
     */
    public boolean run(String input) {
        if (shouldRunAction.test(input)) {
            action.accept(input);
            return true;
        }
        return false;
    }
}
