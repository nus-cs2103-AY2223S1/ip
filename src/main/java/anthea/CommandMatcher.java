package anthea;

import java.util.function.Function;
import java.util.function.Predicate;

import anthea.exception.ChatbotExceptionFunction;

/**
 * This class serves as a way to abstract the idea of making a command
 * as a matching process and an action.
 */
public class CommandMatcher extends StringMatcher<ChatbotResponse> {

    /**
     * Constructs an object that handles checking and executing a command.
     *
     * @param shouldRunAction Predicate to check if the command should be run.
     * @param action Action to run.
     */
    public CommandMatcher(Predicate<String> shouldRunAction, Function<String, ChatbotResponse> action) {
        super(shouldRunAction, action);
        assert shouldRunAction != null;
        assert action != null;
    }

    /**
     * Constructs an object that handles checking and executing a command.
     *
     * @param shouldRunAction Predicate to check if the command should be run.
     * @param action Action to run.
     * @return Constructed CommandMatcher.
     */
    public static CommandMatcher of(Predicate<String> shouldRunAction, ChatbotExceptionFunction<String> action) {
        return new CommandMatcher(shouldRunAction, ChatbotExceptionFunction.toFunction(action));
    }

    /**
     * Constructs an object that handles checking and executing a command.
     *
     * @param prefix Prefix of the command which is checked.
     * @param action Action to run.
     * @return Constructed CommandMatcher.
     */
    public static CommandMatcher of(String prefix, ChatbotExceptionFunction<String> action) {
        assert prefix != null;
        assert action != null;
        return new CommandMatcher((cmd) -> cmd.strip().startsWith(prefix),
                ChatbotExceptionFunction.toFunction(action));
    }
}
