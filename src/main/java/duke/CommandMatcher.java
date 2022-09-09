package duke;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * This class serves as a way to abstract the idea of making a command
 * as a matching process and an action.
 */
public class CommandMatcher {
    private Predicate<String> shouldRunAction;
    private Function<String, DukeResponse> action;

    /**
     * Constructs an object that handles checking and executing a command.
     *
     * @param shouldRunAction Predicate to check if the command should be run.
     * @param action Action to run.
     */
    public CommandMatcher(Predicate<String> shouldRunAction, Function<String, DukeResponse> action) {
        assert shouldRunAction != null;
        assert action != null;
        this.shouldRunAction = shouldRunAction;
        this.action = action;
    }

    /**
     * Constructs an object that handles checking and executing a command.
     *
     * @param shouldRunAction Predicate to check if the command should be run.
     * @param action Action to run.
     * @return Constructed CommandMatcher.
     */
    public static CommandMatcher of(Predicate<String> shouldRunAction, DukeExceptionFunction<String> action) {
        return new CommandMatcher(shouldRunAction, DukeExceptionFunction.toFunction(action));
    }

    /**
     * Constructs an object that handles checking and executing a command.
     *
     * @param prefix Prefix of the command which is checked.
     * @param action Action to run.
     * @return Constructed CommandMatcher.
     */
    public static CommandMatcher of(String prefix, DukeExceptionFunction<String> action) {
        assert prefix != null;
        assert action != null;
        return new CommandMatcher((cmd) -> cmd.strip().startsWith(prefix),
                DukeExceptionFunction.toFunction(action));
    }

    /**
     * Checks if the string matches.
     * If it does, it would execute the action.
     *
     * @param input String to check if it is for this command.
     * @return A DukeResponse if the string matches.
     */
    public Optional<DukeResponse> run(String input) {
        assert input != null;
        if (shouldRunAction.test(input)) {
            DukeResponse result = action.apply(input);
            assert result != null;
            return Optional.of(result);
        }
        return Optional.empty();
    }
}
