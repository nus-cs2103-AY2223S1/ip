package anthea;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import anthea.exception.ChatbotException;
import anthea.exception.ChatbotExceptionBiFunction;
import anthea.exception.ChatbotExceptionFunction;

/**
 * Makes a command matcher based on prefix.
 * It splits the slash options "/by /at" and other parts as a Map&lt;String, String&gt;
 * and trims the string involved.
 * The action takes the String and a map containing the options.
 */
public class PrefixCommandMatcher extends CommandMatcher {
    /**
     * Creates a command matcher that tries to match a prefix.
     *
     * @param prefix Prefix to match.
     * @param action Action to do.
     */
    public PrefixCommandMatcher(String prefix, BiFunction<String, Map<String, String>, ChatbotResponse> action) {
        super(makePrefixMatcher(prefix),
                ChatbotExceptionFunction.toFunction((cmd) -> {
                    // preprocessing
                    cmd = cmd.strip();

                    // corner case
                    if (cmd.equals(prefix)) {
                        throw new ChatbotException(new ChatbotResponse(
                                "(>.<') Add a description to your " + prefix + "."));
                    }

                    // map processing
                    String withoutPrefix = cmd.substring(prefix.length() + 1);
                    String[] commandParts = withoutPrefix.split(" /");
                    Map<String, String> map = new HashMap<>();
                    for (int i = 1; i < commandParts.length; i++) {
                        String[] keyAndValue = commandParts[i].split(" ", 2);
                        if (keyAndValue.length == 2) {
                            map.put(keyAndValue[0].strip(), keyAndValue[1]);
                        } else {
                            map.put(keyAndValue[0].strip(), "");
                        }
                    }

                    // another corner case
                    if (commandParts[0].equals("")) {
                        throw new ChatbotException(new ChatbotResponse(
                                "(>.<') The description for " + prefix + " shouldn't be empty."));
                    }

                    // accept
                    return action.apply(commandParts[0], map);
                }));
        assert prefix != null;
        assert action != null;
    }

    private static Predicate<String> makePrefixMatcher(String prefix) {
        return (cmd) -> cmd.strip().startsWith(prefix + " ") || cmd.strip().equals(prefix);
    }

    /**
     * Creates a command matcher that tries to match a prefix.
     *
     * @param prefix Prefix to match.
     * @param action Action to do.
     */
    public static PrefixCommandMatcher of(String prefix, ChatbotExceptionBiFunction<String, Map<String, String>> action) {
        assert prefix != null;
        assert action != null;
        return new PrefixCommandMatcher(prefix,
                ChatbotExceptionBiFunction.toBiFunction(action));
    }
}
