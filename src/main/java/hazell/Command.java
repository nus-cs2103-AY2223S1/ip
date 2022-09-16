package hazell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hazell.exceptions.KwargNotFound;

public class Command {
    /**
     * Represents userinput from user, parsed into arguments (args) and keyword arguments (kwargs).
     *
     * PARSING
     * Format: [ARGS]... [/OPTION VALUE]...
     * Options are specified by a slash (/) followed by a word. All non-option words that come after it
     * are considered to be the value corresponding to that option.
     *
     * LIMITATIONS
     * - Doesn't allow for multiple repeated options
     */

    private final List<String> args;
    private final Map<String, String> kwargs;

    private Command(List<String> args, Map<String, String> kwargs) {
        this.args = args;
        this.kwargs = kwargs;
    }

    /**
     * Factory method for creating instances of the Command object.
     *
     * @param str Userinput
     * @return A Command object
     */
    public static Command parse(String str) {
        String[] splitBySlash = str.split("(?<=\\s)/");
        List<String> args = new ArrayList<>(List.of(splitBySlash[0].split(" ")));

        Map<String, String> kwargs = new HashMap<>();
        for (int i = 0; i < splitBySlash.length - 1; i++) {
            String[] kwargWords = splitBySlash[i + 1].split(" ", 2);
            kwargs.put(kwargWords[0], kwargWords[1]);
        }

        return new Command(args, kwargs);
    }

    /**
     * Gets the first argument.
     *
     * @return The first argument
     */
    public String getFirstArg() {
        return this.args.get(0);
    }

    /**
     * Gets arguments excluding the first argument.
     *
     * @return Arguments excluding first argument.
     */
    public List<String> getTrailingArgs() {
        return this.args.subList(1, this.args.size());
    }

    public String getKwarg(String key) throws KwargNotFound {
        String value = this.kwargs.get(key);
        if (value == null) {
            throw new KwargNotFound(this.args.get(0), key);
        }
        return value;
    }

    @Override
    public String toString() {
        return this.args.toString() +
                "\n" +
                this.kwargs.toString();
    }
}
