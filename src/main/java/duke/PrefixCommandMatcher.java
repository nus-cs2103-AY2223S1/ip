package duke;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Makes a command matcher based on prefix.
 * It splits the slash options "/by /at" and other parts as a Map&lt;String, String&gt;
 * and trims the string involved.
 * The action takes the String and a map containing the options.
 */
public class PrefixCommandMatcher extends CommandMatcher {
    /**
     * Creates a command matcher that tries to match a prefix.
     */
    public PrefixCommandMatcher(String prefix, BiConsumer<String, Map<String, String>> action) {
        super((cmd) -> cmd.strip().startsWith(prefix + " ") || cmd.strip().equals(prefix), (cmd) -> {
            // preprocessing
            cmd = cmd.strip();

            // corner case
            if (cmd.equals(prefix)) {
                Ui.printStyledMessage("(>.<') Add a description to your " + prefix + ".");
                return;
            }

            // map processing
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

            // another corner case
            if (commandParts[0].equals("")) {
                Ui.printStyledMessage("(>.<') The description for " + prefix + " shouldn't be empty.");
                return;
            }

            // accept
            action.accept(commandParts[0].strip(), map);
        });
    }
}
