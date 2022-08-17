import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Makes a command matcher based on prefix
 * It splits the slash options "/by /at" and other parts as a Map&lt;String, String&gt;
 * and trims the string involved. The action takes the String and String
 */
public class PrefixCommandMatcher extends CommandMatcher {
    PrefixCommandMatcher(String prefix, BiConsumer<String, Map<String, String>> action) {
        super((cmd) -> cmd.startsWith(prefix + " ") || cmd.equals(prefix), (cmd) -> {
            if (cmd.equals(prefix)) {
                Duke.messagePrint("(>.<') Add a description to your " + prefix + ".");
                return;
            }
            String withoutPrefix = cmd.split(" ", 2)[1];
            String[] commandParts = withoutPrefix.split(" /");
            Map<String, String> map = new HashMap<>();
            for (int i = 1; i < commandParts.length; i++) {
                String[] keyAndValue = commandParts[i].split(" ", 2);
                if (keyAndValue.length == 2) {
                    map.put(keyAndValue[0].strip(), keyAndValue[1].strip());
                } else {
                    map.put(keyAndValue[0].strip(), "");
                }
            }
            commandParts[0] = commandParts[0].strip();
            if (commandParts[0].equals("")) {
                Duke.messagePrint("(>.<') The description for " + prefix + " shouldn't be empty.");
                return;
            }
            action.accept(commandParts[0].strip(), map);
        });
    }
}
